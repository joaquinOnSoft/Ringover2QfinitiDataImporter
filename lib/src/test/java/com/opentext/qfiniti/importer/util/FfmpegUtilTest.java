package com.opentext.qfiniti.importer.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.Test;

public class FfmpegUtilTest {

	@Test
	public void convertMp32Wav() {
		File mp3 = FileUtil.getFileFromResources("Free_Test_Data_100KB_MP3.mp3");
		File wav = new File(mp3.getParentFile().getAbsolutePath().concat(File.separator).concat("Free_Test_Data_100KB_MP3.wav"));

		int exitValue = FfmpegUtil.convertMp32Wav(mp3, wav);
		assertEquals(0, exitValue);
		assertTrue(wav.exists());
		
		wav.delete();
	}
	

	/**
	 * <strong>NOTE</strong>: This test requires that <i>'ffmpeg'</i> 
	 * is installed and included in the <i>PATH</i>
	 * in the computer that runs the test
	 **/
	@Test	
	public void isFfmpegInPath() {
		assertTrue(FfmpegUtil.isFfmpegInPath());
	}
}
