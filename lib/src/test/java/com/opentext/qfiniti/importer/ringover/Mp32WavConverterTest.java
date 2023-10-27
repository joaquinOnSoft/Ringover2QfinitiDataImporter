package com.opentext.qfiniti.importer.ringover;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.opentext.qfiniti.importer.pojo.CallRecording;
import com.opentext.qfiniti.importer.util.FileUtil;

public class Mp32WavConverterTest {
	static Mp32WavConverter converter;
	
	static final String MP3 = "file_example_MP3_700KB.mp3";
	static final String WAV = "file_example_MP3_700KB.wav";
	
	@BeforeAll
	public static void setup() {
		converter = new Mp32WavConverter();
	}
	
	@Test
	public void convert() {
		File mp3 = FileUtil.getFileFromResources(MP3);
		CallRecording  call = new CallRecording(mp3.getParentFile().getAbsolutePath(), MP3, 42);
		
		List<CallRecording> recordings = new LinkedList<CallRecording>();
		recordings.add(call);
		
		recordings = converter.convert(recordings);
		
		assertNotNull(recordings);
		assertEquals(1, recordings.size());
		assertEquals( WAV, recordings.get(0).getFileName());
	}
	
	@Test
	public void getWavNameFromMp3Name() {
		String wavName = converter.getWavNameFromMp3Name("example.mp3");
		
		assertNotNull(wavName);
		assertEquals("example.wav", wavName);
	}
}
