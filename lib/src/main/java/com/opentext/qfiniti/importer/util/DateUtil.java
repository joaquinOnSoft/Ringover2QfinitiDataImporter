package com.opentext.qfiniti.importer.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author Joaquín Garzón
 * @since 20.2
 */
public class DateUtil {
	/**
	 * Return current time in UTC format, e.g. 2020-05-21T16:30:52.123Z
	 * 
	 * @return current time in UTC format
	 */
	public static String nowToUTC() {
		return Instant.now().toString();
	}

	public static String dateToUTC(Date d) {
		return dateToFormat(d, "yyyy-MM-dd'T'HH:mm:ss'Z'");
	}

	public static String dateToQfinitiFormat(Date d) {
		return dateToFormat(d, "MM/dd/yyyy HH:mm:ss");
	}

	public static String dateToFormat(Date d, String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(d);
	}

	/**
	 * Generate a Date object from a string in UTC format
	 * 
	 * @param utc - String which contains a date in UTC format, e.g.
	 *            "2020-05-21T16:30:52.123Z"
	 * @return Date object from a string in UTC format
	 * @throws ParseException
	 */
	public static Date utcToDate(String utc) throws ParseException {
		return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(utc);
	}

	public static Date strToDate(String strDate, String format) throws ParseException {
		return new SimpleDateFormat(format).parse(strDate);
	}

	/**
	 * Provides a date after adding `numDays` number of days
	 * @param date - Date used as reference
	 * @param numDays - Number of days to add
	 * @return Date plus `numDays` number of days
	 * @see <a href="https://mkyong.com/java/java-how-to-add-days-to-current-date/">Java – How to add days to current date</a>
	 */
	public static Date datePlusXDays(Date date, int numDays) {
		Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, numDays); //same with c.add(Calendar.DAY_OF_MONTH, 1);

        return c.getTime();
	}
}