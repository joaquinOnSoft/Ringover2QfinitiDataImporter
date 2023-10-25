package com.opentext.qfiniti.importer.util;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;

public class FfmpegUtil {
	/**
	 * Check existence of `ffmpeg` in the PATH.
	 * 
	 * This method is operative system independent!!!
	 */ 
	public static boolean isFfmpegInPath() {
		// `ffmpeg` executable name can change depending on the operative system
		String exec = "ffmpeg";

		if (OSUtil.isWindows()) {
			exec = "ffmpeg.exe";
		}

		return isFfmpegInPath(exec);
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
