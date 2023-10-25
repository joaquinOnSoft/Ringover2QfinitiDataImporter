package com.opentext.qfiniti.importer.ringover;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class Mp32WavConverterTest {
	static Mp32WavConverter converter;
	
	@BeforeAll
	public static void setup() {
		converter = new Mp32WavConverter();
	}
	
	@Test
	public void getWavNameFromMp3Name() {
		String wavName = converter.getWavNameFromMp3Name("example.mp3");
		
		assertNotNull(wavName);
		assertEquals("example.wav", wavName);
	}
}
