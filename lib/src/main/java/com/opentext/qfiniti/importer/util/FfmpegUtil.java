package com.opentext.qfiniti.importer.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class FfmpegUtil {
	private static final Logger log = LogManager.getLogger(FfmpegUtil.class);

	public static int convertMp32Wav(File mp3, File wav) {
		int exitValue = -1;
		
		// -i url (input)  input file url
		// -y (global)     Overwrite output files without asking.
		// https://ffmpeg.org/ffmpeg.html#Main-options
		String[] cmd={getFfmpegExeName(),"-y", "-i", mp3.getAbsolutePath(), wav.getAbsolutePath()};

		
		try {
			Process process = Runtime.getRuntime().exec(cmd);
			exitValue = process.waitFor();			
		} catch (IOException e) {
			log.error("Unable to convert mp3 file " + mp3.getAbsolutePath() + " to " +wav.getAbsolutePath(), e);
		} catch (InterruptedException e) {
			log.error("Conversion interrupted. mp3 file " + mp3.getAbsolutePath() + " to " +wav.getAbsolutePath(), e);			
		}

		return exitValue; 
	}
	
	public static String getFfmpegExeName() {
		// `ffmpeg` executable name can change depending on the operative system
		String exec = "ffmpeg";

		if (OSUtil.isWindows()) {
			exec = "ffmpeg.exe";
		}
		
		return exec;
	}
	
	/**
	 * Check existence of `ffmpeg` in the PATH.
	 * 
	 * This method is operative system independent!!!
	 */ 
	public static boolean isFfmpegInPath() {
		return isFfmpegInPath(getFfmpegExeName());
	}

	/**
	 * Check existence of `ffprobe` in the PATH
	 * 
	 * <strong>NOTE<strong>: 
	 * @see https://stackoverflow.com/questions/934191/how-to-check-existence-of-a-program-in-the-path
	 * @see com.opentext.qfiniti.importer.io.metadata.JaffreeMetadataExtractor#isFfmpegInPath
	 **/
	private static boolean isFfmpegInPath(String exec) {
		 return java.util.stream.Stream
				.of(System.getenv("PATH").split(Pattern.quote(File.pathSeparator)))
				.map(Paths::get)
				.anyMatch(path -> Files.exists(path.resolve(exec)));
	}
}
