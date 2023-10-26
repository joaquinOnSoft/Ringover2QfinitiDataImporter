package com.opentext.qfiniti.importer.pojo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CallRecordingTest {

	static CallRecording c1, c2, c3;
	
	@BeforeAll
	public static void setup() {
		c1 = new CallRecording();
		c1.setFileName("68e5e794-06e1-426b-afc3-7eacbbd0e835-12-01-23-12h37-3468999888777-34670999888777.mp3");
		c1.setAni("3468999888777");
		c1.setDnis("34670999888777");
		c1.setDateTime(LocalDateTime.now());
		c1.setTeamMemberName("Snow, John");
		
		c2 = c1;
		
		c3 = new CallRecording();
		c3.setFileName("98e5e794-82f2-ffff-afc3-6eabcce0f834-12-02-23-12h39-3468111222333-34670666555444.mp3");
		c3.setAni("3468111222333");
		c3.setDnis("34670666555444");
		c3.setDateTime(LocalDateTime.now());
		c3.setTeamMemberName("Targaryen, Daenerys");	
		c3.setPathName("\\\\myserver\\calls");
	}
	
	@Test
	public void equals() {
		assertEquals(c1, c1);
		assertEquals(c1, c2);		
	}
	
	@Test
	public void notEquals() {		
		assertNotEquals(c1, c3);
		assertNotEquals(c1, "hello");
	}	
}
