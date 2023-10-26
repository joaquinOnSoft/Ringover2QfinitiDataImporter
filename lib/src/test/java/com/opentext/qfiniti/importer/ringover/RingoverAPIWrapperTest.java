package com.opentext.qfiniti.importer.ringover;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.opentext.qfiniti.importer.pojo.CallRecording;
import com.opentext.qfiniti.importer.util.DateUtil;
import com.opentext.qfiniti.importer.util.FileUtil;

public class RingoverAPIWrapperTest {

	private static RingoverAPIWrapper wrapper = null;
	
	@BeforeAll
	public static void beforeAll() {
		wrapper = new RingoverAPIWrapper();
	}
	
	@Test
	public void getAllCalls() {
		List<CallRecording> recordings = null;
		Date end = null;
		try {
			end = DateUtil.strToDate("2023-09-15 00:00:00", "yyyy-MM-dd HH:mm:ss");
		} catch (ParseException e) {
			fail("Invalid end date format. ", e);
		}
		
		Date start = DateUtil.datePlusXDays(end, -3);
		
		try {
			recordings = wrapper.getAllCalls(start, end, 10, CallType.ANSWERED, false);
		} catch (Exception e) {
			fail(e);
		}
		
		assertNotNull(recordings);
		assertEquals(49, recordings.size());
		
		//Remove mp3 files downloaded during the test
		File[] mp3Files = FileUtil.filterFilesByExtension(".", ".mp3");
		for(File mp3: mp3Files) {
			FileUtil.deleteFile(mp3.getAbsolutePath());
		}		
	}
}
