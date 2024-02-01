package com.opentext.qfiniti.importer.ringover;

import java.util.Calendar;
import java.util.Date;

import com.opentext.qfiniti.importer.util.DateUtil;

public class RingoverImporterOptions {
	private boolean removeMp3;
	private boolean wavConversion;
	private boolean discardCallsWithourAudio;
	private Date to;
	private Date from;
	private CallType callType;
	private String outputFileName;
	private String uncPath = null;
	
	public RingoverImporterOptions() {
		removeMp3 = false;
		
		wavConversion = false;
		
		discardCallsWithourAudio = false;
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY,0);
		cal.set(Calendar.MINUTE,0);
		cal.set(Calendar.SECOND,0);
		cal.set(Calendar.MILLISECOND,0);
		
		to = cal.getTime();
		from = DateUtil.datePlusXDays(to, -1);
		
		callType = CallType.ANSWERED;
		
		outputFileName = "calls-" 
				+ DateUtil.dateToFormat(Calendar.getInstance().getTime(), "yyyyMMdd")
				+ ".xls";
	}

	public boolean isRemoveMp3() {
		return removeMp3;
	}

	public void setRemoveMp3(boolean removeMp3) {
		this.removeMp3 = removeMp3;
	}

	public boolean isWavConversion() {
		return wavConversion;
	}

	public void setWavConversion(boolean wavConversion) {
		this.wavConversion = wavConversion;
	}

	public boolean isDiscardCallsWithourAudio() {
		return discardCallsWithourAudio;
	}

	public void setDiscardCallsWithourAudio(boolean discardCallsWithourAudio) {
		this.discardCallsWithourAudio = discardCallsWithourAudio;
	}

	public Date getTo() {
		return to;
	}

	public void setTo(Date to) {
		this.to = to;
	}

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public CallType getCallType() {
		return callType;
	}

	public void setCallType(CallType callType) {
		this.callType = callType;
	}

	public String getOutputFileName() {
		return outputFileName;
	}

	public void setOutputFileName(String outputFileName) {
		this.outputFileName = outputFileName;
	}

	public String getUncPath() {
		return uncPath;
	}

	public void setUncPath(String uncPath) {
		this.uncPath = uncPath;
	}
}
