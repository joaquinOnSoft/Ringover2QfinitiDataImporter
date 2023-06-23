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
	private static final Logger log = LogManager.getLogger(Ringover2QfinitiDataImporterLauncher.class);

	public static void main(String[] args) {
		Options options = new Options();

		Option fromOption = new Option("f", "from", true, "From date. Format: yyyymmdd. Default value: yesterday");
		options.addOption(fromOption);

		Option toOption = new Option("t", "to", true, "To date. Format: yyyymmdd. Default value: today");
		options.addOption(toOption);

		Option callTypeOption = new Option("c", "callType", true,
				"Call type. Used to filter certain types of call. Possible values: \n"
						+ "\t 'ANSWERED':  filters answered calls. Default value. \n"
						+ "\t 'MISSED':    filters missed calls. \n"  						
						+ "\t 'OUT':       filters outgoing calls. \n"
						+ "\t 'VOICEMAIL': filters calls ending on voicemail.");
		options.addOption(callTypeOption);

		Option outputOption = new Option("o", "output", true, "Output file name. Default value: calls-yyyyMMdd.xls");
		options.addOption(outputOption);

		Option uncOption = new Option("u", "unc", true, "Universal Naming Convention (UNC) path to store the call recording files, i.e. \\\\SERVER\\recordings");
		uncOption.setRequired(true);
		options.addOption(uncOption);		
		
		CommandLineParser parser = new DefaultParser();
		HelpFormatter formatter = new HelpFormatter();
		CommandLine cmd = null;

		try {
			cmd = parser.parse(options, args);

			Date to = Calendar.getInstance().getTime();
			Date from = DateUtil.datePlusXDays(to, -1);
			CallType cType = CallType.ANSWERED;
			String outputFileName = "calls-" 
					+ DateUtil.dateToFormat(Calendar.getInstance().getTime(), "yyyyMMdd")
					+ ".xls";
			String uncPath = null;

			if (cmd.hasOption("from") || cmd.hasOption("f")) {
				String fromStr = cmd.getOptionValue("from");
				if (fromStr != null) {
					from = DateUtil.strToDate(fromStr, "yyyyMMdd");
				}
			}

			if (cmd.hasOption("to") || cmd.hasOption("t")) {
				String toStr = cmd.getOptionValue("to");
				if (toStr != null) {
					to = DateUtil.strToDate(toStr, "yyyyMMdd");
				}
			}

			if (cmd.hasOption("callType") || cmd.hasOption("c")) {
				String callTypeStr = cmd.getOptionValue("callType");
				if (callTypeStr != null) {
					cType = CallType.valueOf(callTypeStr);
				}
			}

			if (cmd.hasOption("output") || cmd.hasOption("o")) {
				outputFileName = cmd.getOptionValue("output");
			}

			if (cmd.hasOption("unc") || cmd.hasOption("u")) {
				uncPath = cmd.getOptionValue("unc");
			}			
						
			RingoverAPIWrapper api = new RingoverAPIWrapper(uncPath);
			List<CallRecording> recordings = api.getAllCalls(from, to, cType);

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
					"java -jar Ringover2QfinitiDataImporter23.2.jar -from 20230601 -to 20230621 -c ANSWERED", options);

			exitInError(e.getMessage());
		}
	}

	private static void exitInError(String msg) {
		log.error(msg);
		System.err.println(msg);
		System.exit(-1);
	}
}
