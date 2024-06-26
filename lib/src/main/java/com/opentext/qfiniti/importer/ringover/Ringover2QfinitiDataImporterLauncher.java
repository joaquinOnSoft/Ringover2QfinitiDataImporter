package com.opentext.qfiniti.importer.ringover;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.opentext.qfiniti.importer.io.ExcelWriter;
import com.opentext.qfiniti.importer.pojo.CallRecording;
import com.opentext.qfiniti.importer.util.DateUtil;
import com.opentext.qfiniti.importer.util.FfmpegUtil;
import com.opentext.qfiniti.importer.util.FileUtil;

public class Ringover2QfinitiDataImporterLauncher {

	private static final String MP3_EXTENSION = ".mp3";

	/** From date. Format: yyyymmdd. Default value: yesterday */
	private static final String LONG_OPT_FROM = "from";
	private static final String SHORT_OPT_FROM = "f";

	/** To date. Format: yyyymmdd. Default value: today */
	private static final String LONG_OPT_TO = "to";
	private static final String SHORT_OPT_TO = "t";

	/**
	 * Call type. Used to filter certain types of call. Possible values: 'ANSWERED':
	 * filters answered calls. Default value. 'MISSED': filters missed calls. 'OUT':
	 * filters outgoing calls. 'VOICEMAIL': filters calls ending on voicemail.
	 */
	private static final String LONG_OPT_CALL_TYPE = "callType";
	private static final String SHORT_OPT_CALL_TYPE = "c";

	/**
	 * Discard calls without audio file associated (false by default)
	 */
	private static final String LONG_OPT_DISCARD = "discard";
	private static final String SHORT_OPT_DISCARD = "d";

	/**
	 * Universal Naming Convention (UNC) path to store the call recording files,
	 * i.e. \\SERVER\recordings
	 */
	private static final String LONG_OPT_UNC = "unc";
	private static final String SHORT_OPT_UNC = "u";

	/**
	 * Output file name. Default value: calls-yyyyMMdd.xls
	 */
	private static final String LONG_OPT_OUTPUT = "output";
	private static final String SHORT_OPT_OUTPUT = "o";

	/**
	 * Apply .mp3 to .wav conversion (using ffmpeg). NOTE: ffmpeg must be in the
	 * PATH
	 */
	private static final String LONG_OPT_WAV_CONVERSION = "wav";
	private static final String SHORT_OPT_WAV_CONVERSION = "w";

	/**
	 * Remove .mp3 files once the download is completed and the Excel file has been
	 * generated. (Recommended when --wav argument is used)
	 */
	private static final String LONG_OPT_REMOVE_MP3 = "remove";
	private static final String SHORT_OPT_REMOVE_MP3 = "r";

	private static final Logger log = LogManager.getLogger(Ringover2QfinitiDataImporterLauncher.class);

	public static void main(String[] args) {
		Options options = createOptions();

		CommandLineParser parser = new DefaultParser();
		HelpFormatter formatter = new HelpFormatter();
		CommandLine cmd = null;

		try {
			cmd = parser.parse(options, args);

			RingoverImporterOptions params = manageOptions(cmd);

			//
			// The magic happens here
			//
			System.out.println("Downloading call recordings...");
			
			log.info("===== " + DateUtil.nowToUTC() + " =====");
			log.info("      Downloading call recordings...      ");
			log.info("==========================================");

			RingoverAPIWrapper api = new RingoverAPIWrapper(params.getUncPath());
			List<CallRecording> recordings = api.getAllCalls(params.getFrom(), params.getTo(), params.getCallType(),
					params.isDiscardCallsWithourAudio());

			if (recordings == null || recordings.size() == 0) {
				System.out.println("No call recordings were found in the given period!");
			} else {
				System.out.println("Call recordings found: " + recordings.size());

				if (params.isWavConversion() == true) {
					System.out.println("MP3 to WAV conversion...");
					recordings = convertMp32WavFiles(recordings);
				}

				System.out.println("Generating Excel...");
				generateExcelFile(recordings, params.getOutputFileName());

				if (params.isRemoveMp3() == true) {
					System.out.println("Deleting MP3 files...");
					removeMp3Files(params.getUncPath());
				}

				System.out.println("That's all folks!!!");
			}
		} catch (org.apache.commons.cli.ParseException | java.text.ParseException | IllegalArgumentException
				| IOException | InvalidFormatException e) {
			formatter.printHelp(
					"java -jar Ringover2QfinitiDataImporter-24.02.02.jar --discard --wave --remove --from 20230601 --to 20230701 -c ANSWERED",
					options);

			exitInError(e.getMessage());
		}
	}

	/**
	 * Create command line options
	 * 
	 * @return command line options
	 */
	private static Options createOptions() {
		Options options = new Options();

		Option fromOption = new Option(SHORT_OPT_FROM, LONG_OPT_FROM, true,
				"From date (included). Format: yyyymmdd. Default value: yesterday");
		options.addOption(fromOption);

		Option toOption = new Option(SHORT_OPT_TO, LONG_OPT_TO, true,
				"To date (excluded). Format: yyyymmdd. Default value: today");
		options.addOption(toOption);

		Option callTypeOption = new Option(SHORT_OPT_CALL_TYPE, LONG_OPT_CALL_TYPE, true,
				"Call type. Used to filter certain types of call. Possible values: \n"
						+ "\t 'ANSWERED':  filters answered calls. Default value. \n"
						+ "\t 'MISSED':    filters missed calls. \n" + "\t 'OUT':       filters outgoing calls. \n"
						+ "\t 'VOICEMAIL': filters calls ending on voicemail.");
		options.addOption(callTypeOption);

		Option outputOption = new Option(SHORT_OPT_OUTPUT, LONG_OPT_OUTPUT, true,
				"Output file name. Default value: calls-yyyyMMdd.xls");
		options.addOption(outputOption);

		Option uncOption = new Option(SHORT_OPT_UNC, LONG_OPT_UNC, true,
				"Universal Naming Convention (UNC) path to store the call recording files, i.e. \\\\SERVER\\recordings");
		uncOption.setRequired(true);
		options.addOption(uncOption);

		Option discardOption = new Option(SHORT_OPT_DISCARD, LONG_OPT_DISCARD, false,
				"Discard calls without audio file associated (false by default)");
		options.addOption(discardOption);

		Option wavConversionOption = new Option(SHORT_OPT_WAV_CONVERSION, LONG_OPT_WAV_CONVERSION, false,
				"Apply .mp3 to .wav conversion (using ffmpeg)");
		options.addOption(wavConversionOption);

		Option removeMp3Option = new Option(SHORT_OPT_REMOVE_MP3, LONG_OPT_REMOVE_MP3, false,
				"Remove .mp3 files once the download is completed and the Excel file has been generated. (Recommended when --wav argument is used)");
		options.addOption(removeMp3Option);

		return options;
	}

	private static RingoverImporterOptions manageOptions(CommandLine cmd)
			throws IllegalArgumentException, ParseException {
		
		RingoverImporterOptions opts = new RingoverImporterOptions();

		Date from = null; 
		Date to = null;
		Date now = GregorianCalendar.getInstance().getTime();

		if (cmd.hasOption(LONG_OPT_FROM) || cmd.hasOption(SHORT_OPT_FROM)) {
			String fromStr = cmd.getOptionValue(LONG_OPT_FROM);
			if (fromStr != null) {
				from = DateUtil.strToDate(fromStr, "yyyyMMdd");
				if(from.after(now)) {
					throw new IllegalArgumentException("FROM date '" + from + "' can't be posterior to CURRENT date");
				}
				opts.setFrom(from);
			}
		}

		if (cmd.hasOption(LONG_OPT_TO) || cmd.hasOption(SHORT_OPT_TO)) {
			String toStr = cmd.getOptionValue(LONG_OPT_TO);
			if (toStr != null) {
				to = DateUtil.strToDate(toStr, "yyyyMMdd");
				if(to.after(now)) {
					throw new IllegalArgumentException("TO date '" + from + "' can't be posterior to CURRENT date");
				}
				else if(from != null && to.before(from)) {
					throw new IllegalArgumentException("TO date '" + from + "' can't be posterior to FROM date");
				}
				opts.setTo(to);
			}
		}

		if (cmd.hasOption(LONG_OPT_CALL_TYPE) || cmd.hasOption(SHORT_OPT_CALL_TYPE)) {
			String callTypeStr = cmd.getOptionValue(LONG_OPT_CALL_TYPE);
			if (callTypeStr != null) {
				opts.setCallType(CallType.valueOf(callTypeStr));
			}
		}

		if (cmd.hasOption(LONG_OPT_OUTPUT) || cmd.hasOption(SHORT_OPT_OUTPUT)) {
			opts.setOutputFileName(cmd.getOptionValue(LONG_OPT_OUTPUT));
		}

		if (cmd.hasOption(LONG_OPT_UNC) || cmd.hasOption(SHORT_OPT_UNC)) {
			opts.setUncPath(cmd.getOptionValue(LONG_OPT_UNC));
		}

		if (cmd.hasOption(LONG_OPT_DISCARD) || cmd.hasOption(SHORT_OPT_DISCARD)) {
			opts.setDiscardCallsWithourAudio(true);
		}

		if (cmd.hasOption(LONG_OPT_WAV_CONVERSION) || cmd.hasOption(SHORT_OPT_WAV_CONVERSION)) {
			opts.setWavConversion(true);

			if (!FfmpegUtil.isFfmpegInPath()) {
				exitInError(
						" 'ffmpeg' NOT found in PATH.\n It's mandatory when using --wav argument.\n Update your PATH and try again.\n");
			}
		}

		if (cmd.hasOption(LONG_OPT_REMOVE_MP3) || cmd.hasOption(SHORT_OPT_REMOVE_MP3)) {
			opts.setRemoveMp3(true);
		}

		return opts;
	}

	private static List<CallRecording> convertMp32WavFiles(List<CallRecording> recordings) {
		log.debug("MP3 to WAV conversion...");

		Mp32WavConverter converter = new Mp32WavConverter();
		recordings = converter.convert(recordings);
		return recordings;
	}

	private static void generateExcelFile(List<CallRecording> recordings, String outputFileName)
			throws IOException, InvalidFormatException {

		CallRecording recording = recordings.get(0);

		ExcelWriter writer = new ExcelWriter();
		writer.write(recording.getHeaders(), recordings, outputFileName);
	}

	private static void removeMp3Files(String uncPath) {
		File[] mp3Files = FileUtil.filterFilesByExtension(uncPath, MP3_EXTENSION);

		for (File mp3 : mp3Files) {
			log.debug("Deleting MP3: " + mp3.getAbsolutePath());
			mp3.delete();
		}
	}

	private static void exitInError(String msg) {
		log.error(msg);
		System.err.println(msg);
		System.exit(-1);
	}
}
