package com.opentext.qfiniti.importer.ringover;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
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

public class Ringover2QfinitiDataImporterLauncher {

	/** From date. Format: yyyymmdd. Default value: yesterday */
	private static final String LONG_OPT_FROM = "from";
	private static final String SHORT_OPT_FROM = "f";
	
	/** To date. Format: yyyymmdd. Default value: today */
	private static final String LONG_OPT_TO = "to";
	private static final String SHORT_OPT_TO = "t";
	
	/**
	 * Call type. Used to filter certain types of call. Possible values:
	 * 		'ANSWERED':  filters answered calls. Default value.
	 * 		'MISSED':    filters missed calls.			
	 * 		'OUT':       filters outgoing calls. 
	 * 		'VOICEMAIL': filters calls ending on voicemail.
	 */
	private static final String LONG_OPT_CALL_TYPE = "callType";
	private static final String SHORT_OPT_CALL_TYPE = "c";	
	
	/**
	 * Discard calls without audio file associated (false by default)
	 */
	private static final String LONG_OPT_DISCARD = "discard";
	private static final String SHORT_OPT_DISCARD = "d";

	
	/**
	 * Universal Naming Convention (UNC) path to store the call recording files, i.e. \\SERVER\recordings
	 */
	private static final String LONG_OPT_UNC = "unc";
	private static final String SHORT_OPT_UNC = "u";	
	
	/**
	 * Output file name. Default value: calls-yyyyMMdd.xls
	 */
	private static final String LONG_OPT_OUTPUT = "output";
	private static final String SHORT_OPT_OUTPUT = "o";	
	
	private static final Logger log = LogManager.getLogger(Ringover2QfinitiDataImporterLauncher.class);

	public static void main(String[] args) {
		Options options = new Options();

		Option fromOption = new Option(SHORT_OPT_FROM, LONG_OPT_FROM, true, "From date. Format: yyyymmdd. Default value: yesterday");
		options.addOption(fromOption);

		Option toOption = new Option(SHORT_OPT_TO, LONG_OPT_TO, true, "To date. Format: yyyymmdd. Default value: today");
		options.addOption(toOption);

		Option callTypeOption = new Option(SHORT_OPT_CALL_TYPE, LONG_OPT_CALL_TYPE, true,
				"Call type. Used to filter certain types of call. Possible values: \n"
						+ "\t 'ANSWERED':  filters answered calls. Default value. \n"
						+ "\t 'MISSED':    filters missed calls. \n"  						
						+ "\t 'OUT':       filters outgoing calls. \n"
						+ "\t 'VOICEMAIL': filters calls ending on voicemail.");
		options.addOption(callTypeOption);

		Option outputOption = new Option(SHORT_OPT_OUTPUT, LONG_OPT_OUTPUT, true, "Output file name. Default value: calls-yyyyMMdd.xls");
		options.addOption(outputOption);

		Option uncOption = new Option(SHORT_OPT_UNC, LONG_OPT_UNC, true, "Universal Naming Convention (UNC) path to store the call recording files, i.e. \\\\SERVER\\recordings");
		uncOption.setRequired(true);
		options.addOption(uncOption);		
		
		Option discardOption = new Option(SHORT_OPT_DISCARD, LONG_OPT_DISCARD, false, "Discard calls without audio file associated (false by default)");
		options.addOption(discardOption);		
		
		CommandLineParser parser = new DefaultParser();
		HelpFormatter formatter = new HelpFormatter();
		CommandLine cmd = null;

		try {
			cmd = parser.parse(options, args);

			boolean discardCallsWithourAudio = false;
			Date to = Calendar.getInstance().getTime();
			Date from = DateUtil.datePlusXDays(to, -1);
			CallType cType = CallType.ANSWERED;
			String outputFileName = "calls-" 
					+ DateUtil.dateToFormat(Calendar.getInstance().getTime(), "yyyyMMdd")
					+ ".xls";
			String uncPath = null;

			if (cmd.hasOption(LONG_OPT_FROM) || cmd.hasOption(SHORT_OPT_FROM)) {
				String fromStr = cmd.getOptionValue(LONG_OPT_FROM);
				if (fromStr != null) {
					from = DateUtil.strToDate(fromStr, "yyyyMMdd");
				}
			}

			if (cmd.hasOption(LONG_OPT_TO) || cmd.hasOption(SHORT_OPT_TO)) {
				String toStr = cmd.getOptionValue(LONG_OPT_TO);
				if (toStr != null) {
					to = DateUtil.strToDate(toStr, "yyyyMMdd");
				}
			}

			if (cmd.hasOption(LONG_OPT_CALL_TYPE) || cmd.hasOption(SHORT_OPT_CALL_TYPE)) {
				String callTypeStr = cmd.getOptionValue(LONG_OPT_CALL_TYPE);
				if (callTypeStr != null) {
					cType = CallType.valueOf(callTypeStr);
				}
			}

			if (cmd.hasOption(LONG_OPT_OUTPUT) || cmd.hasOption(SHORT_OPT_OUTPUT)) {
				outputFileName = cmd.getOptionValue(LONG_OPT_OUTPUT);
			}

			if (cmd.hasOption(LONG_OPT_UNC) || cmd.hasOption(SHORT_OPT_UNC)) {
				uncPath = cmd.getOptionValue(LONG_OPT_UNC);
			}			
			
			if (cmd.hasOption(LONG_OPT_DISCARD) || cmd.hasOption(SHORT_OPT_DISCARD)) {
				discardCallsWithourAudio = true;
			}			
						
			RingoverAPIWrapper api = new RingoverAPIWrapper(uncPath);
			List<CallRecording> recordings = api.getAllCalls(from, to, cType, discardCallsWithourAudio);

			if (recordings == null || recordings.size() == 0) {
				System.out.println("No call recordings were found in the given period!");
			} else {
				System.out.println("Call recordings found: " + recordings.size());

				CallRecording recording = recordings.get(0);

				ExcelWriter writer = new ExcelWriter();
				writer.write(recording.getHeaders(), recordings, outputFileName);
				
				System.out.println("That's all folks!!!");				
			}
		} catch (org.apache.commons.cli.ParseException | java.text.ParseException | IllegalArgumentException
				| IOException | InvalidFormatException e) {
			formatter.printHelp(
					"java -jar Ringover2QfinitiDataImporter-23.10.jar -from 20230601 -to 20230621 -c ANSWERED", options);

			exitInError(e.getMessage());
		}
	}

	private static void exitInError(String msg) {
		log.error(msg);
		System.err.println(msg);
		System.exit(-1);
	}
}
