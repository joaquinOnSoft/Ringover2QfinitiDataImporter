package com.opentext.qfiniti.importer.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class FfmpegUtil {
	private static final Logger log = LogManager.getLogger(FfmpegUtil.class);

	/**
	 * NOTE: mp3 files recorded by Ringover have these characteristics:
	 * 
	 * 	Stream #0:0: Audio: mp3, 16000 Hz, stereo, fltp, 64 kb/s
	 * 
	 * @param mp3
	 * @param wav
	 * @return
	 */
	public static int convertMp32Wav(File mp3, File wav) {
		int exitValue = -1;

		// usage: ffmpeg [options] [[infile options] -i infile]... {[outfile options] outfile}...
		
		//   -y (global)     Overwrite output files without asking.		
		//   -i url (input)  input file url
		//   -ac channels        set number of audio channels
		//   -acodec codec       force audio codec ('copy' to copy stream)
		//   -ar rate            set audio sampling rate (in Hz)
		
		// https://ffmpeg.org/ffmpeg.html#Main-options
		//
		// SEE: 
		// https://gist.github.com/vunb/7349145
		String[] cmd={getFfmpegExeName(),
				"-y", 
				"-i", 						
				mp3.getAbsolutePath(),
				"-acodec",
				"pcm_s16le",
				"-ac",
				"2",
				"-ar",
				"16000",
				wav.getAbsolutePath()};

		try {
			Process process = Runtime.getRuntime().exec(cmd);

			BufferedReader stdError = new BufferedReader(new 
					InputStreamReader(process.getErrorStream()));		
			
			BufferedReader stdInput = new BufferedReader(new 
					InputStreamReader(process.getInputStream()));

			// Read any errors from the attempted command
			String s = null;
			while ((s = stdError.readLine()) != null) {
				log.info(s);
			}
			
			// Read the output from the command			
			
			while ((s = stdInput.readLine()) != null) {
				log.debug(s);
			}

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
