package com.opentext.qfiniti.importer.ringover;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.opentext.qfiniti.importer.pojo.CallRecording;
import com.opentext.qfiniti.importer.util.DateUtil;

public class RingoverAPIWrapperTest {

	private static RingoverAPIWrapper wrapper = null;
	
	@BeforeAll
	public static void beforeAll() {
		wrapper = new RingoverAPIWrapper();
	}
	
	@Test
	public void getAllCalls() {
		List<CallRecording> recordings = null;
		Date end = Calendar.getInstance().getTime();
		Date start = DateUtil.datePlusXDays(end, -15);
		
		try {
			recordings = wrapper.getAllCalls(start, end, 1, CallType.ANSWERED);
		} catch (Exception e) {
			fail(e);
		}
		
		assertNotNull(recordings);
		assertEquals(5, recordings.size());
	}
}
