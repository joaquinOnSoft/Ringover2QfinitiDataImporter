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
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
		
		Option callTypeOption = new Option("c", "callType", true, "Call type. Used to filter certain types of call. Possible values: \n"
				+ "\t 'ANSWERED' filters answered calls. Default value. \n"
				+ "\t 'MISSED' filters missed calls. \n"
				+ "\t 'OUT' filters outgoing calls. \n"
				+ "\t 'VOICEMAIL' filters calls ending on voicemail.");
		options.addOption(callTypeOption);
		
		CommandLineParser parser = new DefaultParser();
		HelpFormatter formatter = new HelpFormatter();
		CommandLine cmd = null;

		try {	
			cmd = parser.parse(options, args);
			
			Date to = Calendar.getInstance().getTime();
			Date from = DateUtil.datePlusXDays(to, -1);
			CallType cType = CallType.ANSWERED;

			if (cmd.hasOption("from") || cmd.hasOption("f")) {
				String fromStr = cmd.getOptionValue("from");
				if(fromStr != null) {
					from = DateUtil.strToDate(fromStr, "yyyyMMdd");
				}
			}
			
			if (cmd.hasOption("to") || cmd.hasOption("t")) {
				String toStr = cmd.getOptionValue("to");
				if(toStr != null) {
					to = DateUtil.strToDate(toStr, "yyyyMMdd");
				}				
			}			

			if (cmd.hasOption("callType") || cmd.hasOption("c")) {
				String callTypeStr = cmd.getOptionValue("callType");
				if(callTypeStr != null) {
					cType = CallType.valueOf(callTypeStr);
				}				
			}				
			
			RingoverAPIWrapper api = new RingoverAPIWrapper();
			List<CallRecording> recordings = api.getAllCalls(from, to, cType);
		}
		catch (org.apache.commons.cli.ParseException | java.text.ParseException | IllegalArgumentException | IOException e) {
			formatter.printHelp("java -jar Ringover2QfinitiDataImporter23.2.jar -from 20230622 -from 20230621 -c ANSWERED", options);

			exitInError(e.getMessage());	
		}					
	}
	
	private static void exitInError(String msg) {
		log.error(msg);
		System.err.println(msg);
		System.exit(-1);	
	}		
}
