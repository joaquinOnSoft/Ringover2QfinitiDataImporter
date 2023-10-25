package com.opentext.qfiniti.importer.ringover;

import java.io.File;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opentext.qfiniti.importer.pojo.CallRecording;
import com.opentext.qfiniti.importer.util.FfmpegUtil;

public class Mp32WavConverter {
	private static final String WAV_EXTENSION = ".wav";
	private static final String MP3_EXTENSION = ".mp3";

	private static final Logger log = LogManager.getLogger(Mp32WavConverter.class);
	
	/**
	 * Convert all the .mp3 files, included in the call recording 
	 * list, to .wav files and update the file name in the list
	 * @param recordings - call recording list
	 * @return Exit value returned by ffmpeg after the conversion
	 */
	public List<CallRecording> convert(List<CallRecording> recordings){
		if(recordings != null) {
			CallRecording recording = null;
			String path;
			String mp3FileName = null;
			String wavFileName = null;
			File mp3File, wavFile;
			int exitValue = -1;
			
			int size = recordings.size();
			for (int i=0; i<size; i++) {
				recording = recordings.get(i);
				path = recording.getPathName();
				mp3FileName = recording.getFileName();
				wavFileName = getWavNameFromMp3Name(mp3FileName);
				
				mp3File = new File(path.concat(File.separator).concat(mp3FileName));
				wavFile = new File(path.concat(File.separator).concat(wavFileName));
				
				log.info("Converting mp3: " + mp3File.getAbsolutePath());
				
				exitValue = FfmpegUtil.convertMp32Wav(mp3File, wavFile);
				
				log.debug("mp3 to wav conversion. Exit value: " + exitValue);
				
				recordings.get(i).setFileName(wavFileName);				
			}
		}
		
		return recordings;
	}
	
	protected String getWavNameFromMp3Name(String mp3) {
		String wavName = null;
		
		if(mp3 != null) {
			int index = mp3.lastIndexOf(MP3_EXTENSION);
			if(index  > 0) {
				wavName = mp3.substring(0, index) + WAV_EXTENSION;
			}
		}
		
		return wavName;
	}
}
