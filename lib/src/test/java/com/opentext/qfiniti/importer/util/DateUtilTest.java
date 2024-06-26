package com.opentext.qfiniti.importer.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.text.ParseException;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;


public class DateUtilTest {

	private int subStringToInt(String str, int init, int end) {
		return Integer.parseInt(str.substring(init, end));
	}

	@Test
	public void testNowToUTC() {
		Calendar now = GregorianCalendar.getInstance();

		String dateStr = DateUtil.nowToUTC();

		assertNotNull(dateStr);
		assertEquals(now.get(Calendar.YEAR), subStringToInt(dateStr, 0, 4));
		assertEquals(now.get(Calendar.MONTH) + 1, subStringToInt(dateStr, 5, 7));
		assertEquals(now.get(Calendar.DAY_OF_MONTH), subStringToInt(dateStr, 8, 10));
	}

	@Test
	public void testUtcToDate() {
		final String utcRef = "2020-05-21T16:30:52Z";

		try {
			Date utc = DateUtil.utcToDate(utcRef);
			assertEquals(utcRef, DateUtil.dateToUTC(utc));
		} catch (ParseException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testDateToUTC() {
		Calendar now = GregorianCalendar.getInstance();
		String nowStr = DateUtil.dateToUTC(now.getTime());

		assertNotNull(nowStr);
		assertEquals(now.get(Calendar.YEAR), subStringToInt(nowStr, 0, 4));
		assertEquals(now.get(Calendar.MONTH) + 1, subStringToInt(nowStr, 5, 7));
		assertEquals(now.get(Calendar.DAY_OF_MONTH), subStringToInt(nowStr, 8, 10));
	}

	@Test
	public void testStrToDate() {
		final String strDate = "01/12/2020";
		final String format = "dd/MM/yyyy";

		try {
			Date date = DateUtil.strToDate(strDate, format);
			assertEquals("Tue Dec 01 00:00:00 CET 2020", date.toString());
		} catch (ParseException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testStrToDate2() {
		final String strDate = "2023-09-15T08:54:30Z";
		final String format = "yyyy-MM-dd'T'HH:mm:ss'Z'";

		try {
			Date date = DateUtil.strToDate(strDate, format);
			assertEquals("Fri Sep 15 08:54:30 CEST 2023", date.toString());
		} catch (ParseException e) {
			fail(e.getMessage());
		}
	}	
	
	@Test
	public void dateToQfinitiFormat() {
		//2016-09-13T23:30:52.123Z
		Instant now = Instant.now();
		String nowStr = now.toString();
		
		String dateStr = DateUtil.dateToQfinitiFormat(Date.from(now));
		
		assertNotNull(dateStr);
		//dd/MM/yyyy HH:mm:ss
		assertEquals(nowStr.substring(0, 4), dateStr.substring(6, 10)); //year
		assertEquals(nowStr.substring(5, 7), dateStr.substring(0, 2));  //day
		assertEquals(nowStr.substring(8, 10), dateStr.substring(3, 5)); //month
	}
	
	@Test
	public void datePlusXDays() {
		Date now = Calendar.getInstance().getTime();
		
		Date dateFromNow = DateUtil.datePlusXDays(now, 15);
		
		assertTrue(dateFromNow.after(now));
		
		Date now2 = DateUtil.datePlusXDays(dateFromNow, -15);
		
		assertEquals(now.toString(), now2.toString());
	}
}