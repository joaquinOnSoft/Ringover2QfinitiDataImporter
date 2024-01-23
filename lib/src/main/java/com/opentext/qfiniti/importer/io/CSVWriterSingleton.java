package com.opentext.qfiniti.importer.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class CSVWriterSingleton {

	private static CSVWriterSingleton instance;
	private BufferedWriter writer = null;
	
	private static final Logger log = LogManager.getLogger(CSVWriterSingleton.class);

	private CSVWriterSingleton() {
		try {
			writer = new BufferedWriter(new FileWriter("ringover-url.csv", true));
		} catch (IOException e) {
			log.error("Error opening file: ", e);
		}
	}

	public static CSVWriterSingleton getInstance() {
		if (instance == null) {
			instance = new CSVWriterSingleton();
		}

		return instance;
	}
	
	public void write(String text[]) {
		StringBuilder sBuilder = new StringBuilder();
		
		if(text != null) {
			int size = text.length;
			for(int i=0; i<size; i++) {
				sBuilder.append(text[i]);
				
				if(i != size -1) {
					sBuilder.append(",");
				}
			}
		}
		
		write(sBuilder.toString());
	}
	
	public void write(String text) {
		try {
			writer.append(text).append("\n");
			writer.flush();
		} catch (IOException e) {
			log.error("Error writint file: ", e);
		}
	}
	
	@Override
	protected void finalize() {
		if(writer != null) {
		    try {
		    	writer.close();
		    } catch (IOException e) {
		    	log.error("Error closing CSV file: ", e);
		    }
		}
	}	
}
