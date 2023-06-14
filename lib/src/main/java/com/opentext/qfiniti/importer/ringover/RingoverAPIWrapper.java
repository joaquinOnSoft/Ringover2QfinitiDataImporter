package com.opentext.qfiniti.importer.ringover;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opentext.qfiniti.importer.pojo.CallRecording;
import com.opentext.qfiniti.importer.ringover.pojo.calls.Call;
import com.opentext.qfiniti.importer.ringover.pojo.calls.TerminatedCalls;
import com.opentext.qfiniti.importer.util.DateUtil;

public class RingoverAPIWrapper {

	private static final Logger log = LogManager.getLogger(RingoverAPIWrapper.class);
	
	/**
	 * <strong>Get all calls</strong>
	 * <p>
	 * This request allows you to retrieve your terminated calls. All the parameters are optional. 
	 * For a more refined search, please refer to the POST method.
	 * </p>
	 * 
	 * @param startDate - Used to create a time cursor. Must be used with `endDate`
	 * @param endDate - Used to create a time cursor. Must be used with `startDate` and the difference 
	 * between the `startDate` and the `endDate` must not exceed 15 days.
	 * @param limitCount - Restrict the number of returned rows (Max. 1000)
	 * @param callType - Used to filter certain types of call. 'ANSWERED' filters answered calls. 
	 * 'MISSED' filters missed calls. 'OUT' filters outgoing calls. 'VOICEMAIL' filters calls ending on voicemail.
	 * 
	 * @throws IllegalArgumentException - 
	 * @throws IOException - Properties file not found or no permissions 
	 * @throws FileNotFoundException - Properties file not found 
	 * 
	 * @see <a href="https://mkyong.com/java/apache-httpclient-examples/">Apache HttpClient Examples</a>
	 */	
	public List<CallRecording> getAllCalls(Date startDate, Date endDate, 
			int limitCount, CallType callType) throws IllegalArgumentException, FileNotFoundException, IOException{
				
		
		List<CallRecording> recordings = null;
		
		String lastIdReturned = null;
		Date startDateTmp = null;
		Date startDatePlus15Days = null;
		Date endDateTmp = null;
				
		RingoverAPI api = new RingoverAPI();
		
		if(startDate != null && endDate != null) {
			if (endDate.before(startDate)) {
				throw new IllegalArgumentException("End date (" + endDate + ") must be posterior to Start date (" + startDate + ")");
			}
			
			
			startDateTmp = startDate;
			startDatePlus15Days = DateUtil.datePlusXDays(startDate, 15);
			if(endDate.after(startDatePlus15Days)) {
				endDateTmp = startDatePlus15Days;
			}
			else {
				endDateTmp = endDate;
			}
		}
		else if (startDate != null || endDate != null) {
			throw new IllegalArgumentException("You must provide the Start date end End date or none of them.");			
		}
		
		if (limitCount < 0 || limitCount > RingoverAPI.MAX_LIMIT_COUNT) {
			limitCount = RingoverAPI.MAX_LIMIT_COUNT;
		}
		
		TerminatedCalls calls = null;
		int numCallsRetrieved = 0;
		int totalCallCount = 0; 
		int callListCount = 0;
		do {
			calls = api.getAllCalls(startDateTmp, endDateTmp, limitCount, callType, lastIdReturned);
			if(calls != null && calls.getTotalCallCount() > 0) {
				List<CallRecording> recordingsTmp = transform(calls);
				
				if(recordingsTmp != null && recordingsTmp.size() > 0) {
					if(recordings == null) {
						recordings = new LinkedList<CallRecording>();
					}
					
					recordings.addAll(recordingsTmp);
				}
				
				// Update last call Id returned
				totalCallCount = calls.getTotalCallCount(); 
				callListCount = calls.getCallListCount();
				numCallsRetrieved += callListCount;
				if(numCallsRetrieved <= totalCallCount) {
					lastIdReturned = Integer.toString( calls.getCallList().get(callListCount - 1).getCdrId() );
					
					//Warning [Rate limit]: There is a 2-call per second limit applied to each request.
			        try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						log.error("Sleep betweein Ringover API calls.", e);
					}
				}
			}
		}
		while(numCallsRetrieved < totalCallCount);
		
		return recordings;
	}	
		
	/**
	 * Transforms a `TerminatedCalls` object to a List of `CallRecording`
	 * @param calls -  `TerminatedCalls` object returned by Ringover API 
	 * @return List of `CallRecording`
	 */
	private List<CallRecording> transform(TerminatedCalls calls){
		List<CallRecording> recordings = null;

		if(calls != null) {
			recordings = new LinkedList<CallRecording>();
			
			for(Call call: calls.getCallList()) {
				CallRecording recording = new CallRecording();
				
				recording.setPathName(null); // TODO Add path
				recording.setFileName(getFileNameFromURL(call.getRecord())); 								
				recording.setDuration(call.getTotalDuration());
				
				//"start_time": "2023-06-14T09:31:17.73Z"
				Date callStartTime = null;
				try {
					callStartTime = DateUtil.strToDate(call.getStartTime(), "yyyy-MM-dd'T'HH:mm:ss'.'SS'Z'");
				} catch (ParseException e) {
					log.error("Invalid date format. Expected: yyyy-MM-dd'T'HH:mm:ssZ", e);
				}
				//dateTime in format "dd/MM/yyyy HH:mm:ss"
				recording.setDateTime(DateUtil.dateToFormat(callStartTime, "dd/MM/yyyy HH:mm:ss"));
				recording.setTeamMemberName(call.getUser().getLastname() + ", " + call.getUser().getFirstname());
				recording.setGroupHierachy(Integer.toString(call.getUser().getTeamId()));
				// ANIs omitted
				
				// TODO Manage 'to' and 'from' number in IN/OUT calls
				recording.setDnis(call.getToNumber());
				
				recordings.add(recording);
			}
		}
		
		return recordings;
	}
	
	/**
	 * <p>
	 * Provides the name of the call recording file from call recording URL
	 * </p> 
	 * <p>
	 * The URL looks like this: https://cdn.ringover.com/records/0-0/35537d8679f3e2-13-04-19-15h27-33600000000-33180800000.mp3
	 * </p>
	 * @param url - URL to the call recording in mp3 format.
	 * @return The name of the call recording file
	 */
	private String getFileNameFromURL(String url) {
		String fileName = null;
		
		if(url != null) {
			String tokens[] = url.split("/");
			int size = tokens.length;
			
			fileName = tokens[size - 1];
		}
		
		return fileName;
	}
}
