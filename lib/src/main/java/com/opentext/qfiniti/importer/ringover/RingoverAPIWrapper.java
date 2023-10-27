package com.opentext.qfiniti.importer.ringover;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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

	private static final String PROPERTY_USER_DIR = "user.dir";
	private static final String DIRECTION_OUT = "out";
	private static final String DIRECTION_IN = "in";

	// The API documentation set the limit to 1000, but an API call just returns 100
	private static final int MAX_NUMBER_RETURNED_ROWS = 100;

	private static final Logger log = LogManager.getLogger(RingoverAPIWrapper.class);

	private String workingDirectory;

	public RingoverAPIWrapper() {
		this(System.getProperty(PROPERTY_USER_DIR));
	}

	public RingoverAPIWrapper(String workingDirectory) {
		this.workingDirectory = workingDirectory;
	}

	/**
	 * <strong>Get all calls</strong>
	 * <p>
	 * This request allows you to retrieve your terminated calls. All the parameters
	 * are optional. For a more refined search, please refer to the POST method.
	 * </p>
	 * 
	 * @param startDate                Used to create a time cursor. Must be used
	 *                                 with `endDate`
	 * @param endDate                  Used to create a time cursor. Must be used
	 *                                 with `startDate` and the difference between
	 *                                 the `startDate` and the `endDate` must not
	 *                                 exceed 15 days.
	 * @param callType                 Used to filter certain types of call.
	 *                                 'ANSWERED' filters answered calls. 'MISSED'
	 *                                 filters missed calls. 'OUT' filters outgoing
	 *                                 calls. 'VOICEMAIL' filters calls ending on
	 *                                 voicemail.
	 * @param discardCallsWithourAudio Discard calls without audio file associated
	 * 
	 * @throws IllegalArgumentException 
	 * @throws IOException              Properties file not found or no
	 *                                  permissions
	 * @throws FileNotFoundException    Properties file not found
	 * 
	 * @see <a href="https://mkyong.com/java/apache-httpclient-examples/">Apache
	 *      HttpClient Examples</a>
	 */
	public List<CallRecording> getAllCalls(Date startDate, Date endDate, CallType callType,
			boolean discardCallsWithourAudio)
			throws IllegalArgumentException, FileNotFoundException, IOException {

		return getAllCalls(startDate, endDate, MAX_NUMBER_RETURNED_ROWS, callType, discardCallsWithourAudio);
	}

	/**
	 * <strong>Get all calls</strong>
	 * <p>
	 * This request allows you to retrieve your terminated calls. All the parameters
	 * are optional. For a more refined search, please refer to the POST method.
	 * </p>
	 * 
	 * @param startDate                Used to create a time cursor. Must be used
	 *                                 with `endDate`
	 * @param endDate                  Used to create a time cursor. Must be used
	 *                                 with `startDate` and the difference between
	 *                                 the `startDate` and the `endDate` must not
	 *                                 exceed 15 days.
	 * @param limitCount               Restrict the number of returned rows (Max.
	 *                                 1000)
	 * @param callType                 Used to filter certain types of call.
	 *                                 'ANSWERED' filters answered calls. 'MISSED'
	 *                                 filters missed calls. 'OUT' filters outgoing
	 *                                 calls. 'VOICEMAIL' filters calls ending on
	 *                                 voicemail.
	 * @param discardCallsWithourAudio Discard calls without audio file associated
	 * 
	 * @throws IllegalArgumentException
	 * @throws IOException              Properties file not found or no permissions
	 * @throws FileNotFoundException    Properties file not found
	 * 
	 * @see <a href="https://mkyong.com/java/apache-httpclient-examples/">Apache
	 *      HttpClient Examples</a>
	 */
	public List<CallRecording> getAllCalls(Date startDate, Date endDate, int limitCount, CallType callType,
			boolean discardCallsWithourAudio)
			throws IllegalArgumentException, FileNotFoundException, IOException {

		List<CallRecording> recordings = null;

		String lastIdReturned = null;
		Date startDateTmp = null;
		Date startDatePlus15Days = null;
		Date endDateTmp = null;

		//
		// Initialize Ringover API
		//
		RingoverAPI api = new RingoverAPI();

		//
		// Validate parameters
		//
		if (startDate != null && endDate != null) {
			if (endDate.before(startDate)) {
				throw new IllegalArgumentException(
						"End date (" + endDate + ") must be posterior to Start date (" + startDate + ")");
			}

			startDateTmp = startDate;
			startDatePlus15Days = DateUtil.datePlusXDays(startDate, 15);
			if (endDate.after(startDatePlus15Days)) {
				endDateTmp = startDatePlus15Days;
			} else {
				endDateTmp = endDate;
			}
		} else if (startDate != null || endDate != null) {
			throw new IllegalArgumentException("You must provide the Start date end End date or none of them.");
		}

		log.info("Calls FROM " + startDateTmp + " TO " + endDateTmp);

		if (limitCount < 0 || limitCount > RingoverAPI.MAX_LIMIT_COUNT) {
			limitCount = RingoverAPI.MAX_LIMIT_COUNT;
		}

		log.info("Limit count: " + limitCount);

		TerminatedCalls calls = null;
		int numCallsRetrieved = 0;
		int totalCallCount = 0;
		int callListCount = 0;
		boolean firstCallInPeriod = true;

		do {
			do {
				calls = api.getAllCalls(startDateTmp, endDateTmp, limitCount, callType, lastIdReturned);

				log.info("Retrieved # calls: " + calls.getCallListCount());

				if (calls != null && calls.getTotalCallCount() > 0) {
					List<CallRecording> recordingsTmp = transform(calls, discardCallsWithourAudio);

					if (recordingsTmp != null && recordingsTmp.size() > 0) {
						if (recordings == null) {
							recordings = new LinkedList<CallRecording>();
						}

						log.debug("Calls retrieved: " + recordingsTmp.size());
						for(CallRecording c: recordingsTmp) {
							if(recordings.contains(c)) {
								log.info("<-- Call duplicated (SKIPPED): " + c.getId());
							}
							else {
								log.info("--> Call added: " + c.getId());
								recordings.add(c);
							}
						}
						//recordings.addAll(recordingsTmp);
					}

					// Update last call Id returned
					if (firstCallInPeriod) {
						totalCallCount = calls.getTotalCallCount();
						firstCallInPeriod = false;
					}

					callListCount = calls.getCallListCount();
					numCallsRetrieved += callListCount;

					log.info("Total # of calls retrieved: " + numCallsRetrieved);

					if (numCallsRetrieved <= totalCallCount) {
						if (calls.getCallList() != null) {
							lastIdReturned = Integer.toString(calls.getCallList().get(callListCount - 1).getCdrId());

							// Warning [Rate limit]: There is a 2-call per second limit 
							// applied to each request.
							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								log.error("Sleeping between Ringover API calls.", e);
							}
						} else {
							// Force exit loop
							numCallsRetrieved = totalCallCount;
						}
					}
				}
			} while (numCallsRetrieved < totalCallCount);

			startDateTmp = endDateTmp;
			if (DateUtil.datePlusXDays(endDateTmp, 15).after(endDate)) {
				endDateTmp = endDate;
			} else {
				endDateTmp = DateUtil.datePlusXDays(endDateTmp, 15);
			}
		} while (endDateTmp.before(endDate));

		return recordings;
	}

	/**
	 * Transforms a `TerminatedCalls` object to a List of `CallRecording`
	 * 
	 * @param calls - `TerminatedCalls` object returned by Ringover API
	 * @return List of `CallRecording`
	 * @throws IOException
	 */
	private List<CallRecording> transform(TerminatedCalls calls, boolean discardCallsWithourAudio) throws IOException {
		List<CallRecording> recordings = null;

		if (calls != null) {
			recordings = new LinkedList<CallRecording>();

			String direction = null;
			String recordingURL = null;
			String recordingFileName = null;
			if (calls != null && calls.getCallList() != null) {
				for (Call call : calls.getCallList()) {
					CallRecording recording = new CallRecording();

					// Path name should be a Universal Naming Convention (UNC) path
					recording.setPathName(workingDirectory);
					recording.setId(call.getCallId());
					recording.setDuration(call.getTotalDuration());

					//
					// Manage call start time
					//
					// NOTE: Officially the date format follows this format: 
					//    "yyyy-MM-dd'T'HH:mm:ss'.'SS'Z'"
					// 
					// Example: "start_time": "2023-06-14T09:31:17.73Z"
					//
					// But sometimes follows this other format: 
					//    "yyyy-MM-dd'T'HH:mm:ss'Z'"
					Date callStartTime = null;
					String startTimeStr = call.getStartTime();
					try {
						callStartTime = DateUtil.strToDate(startTimeStr, "yyyy-MM-dd'T'HH:mm:ss'.'SS'Z'");
					} catch (ParseException e) {
						log.info("Invalid date format (1). " + startTimeStr
								+ " - Expected: yyyy-MM-dd'T'HH:mm:ss'.'SS'Z'");

						try {
							callStartTime = DateUtil.strToDate(call.getStartTime(), "yyyy-MM-dd'T'HH:mm:ss'Z'");
						} catch (ParseException e2) {
							log.error("Invalid date format (2). Expected: yyyy-MM-dd'T'HH:mm:ssZ", e);
						}
					}

					// dateTime in format "dd/MM/yyyy HH:mm:ss"
					recording.setDateTime(DateUtil.dateToFormat(callStartTime, "dd/MM/yyyy HH:mm:ss"));
					recording.setTeamMemberName(call.getUser().getLastname() + ", " + call.getUser().getFirstname());
					recording.setGroupHierachy(Integer.toString(call.getUser().getTeamId()));
					// ANIs intentionally omitted

					//
					// Set call direction (INBOUND, OUTBOUND, UNKNOWN)
					//
					direction = call.getDirection();
					if (direction != null) {
						switch (direction.toLowerCase()) {
						case DIRECTION_IN:
							recording.setDnis(call.getFromNumber());
							recording.setDirection(CallRecording.DIRECTION_INBOUND);
							break;
						case DIRECTION_OUT:
							recording.setDnis(call.getToNumber());
							recording.setDirection(CallRecording.DIRECTION_OUTBOUND);
							break;
						default:
							recording.setDirection(CallRecording.DIRECTION_UNKNOWN);
						}
					}
					
					//
					// Download call from Ringover CDN
					//
					recordingURL = call.getRecord();
					if (recordingURL != null) {

						log.debug("Downloading recording URL : " + recordingURL);

						recordingFileName = getFileNameFromURL(recordingURL);
						recording.setFileName(recordingFileName);					

						try {
							// Download recording file (mp3) from recording URL
							InputStream in = new URL(recordingURL).openStream();
							Files.copy(in, Paths.get(workingDirectory, recordingFileName),
									StandardCopyOption.REPLACE_EXISTING);
							
							log.info("DOWNLOADED recording URL : " + recordingURL);
						} catch (IOException e) {
							log.error(">>> Unable to download recording file (mp3) from recording URL: " + recordingFileName, e);
							// Set file name to null to force the call skipping
							recording.setFileName(null);
						}
					}
					else {
						log.debug("Recording URL was null. ANI: " + recording.getAni() + " DNI: " + recording.getDnis());
					}
					

					if ((recording.getFileName() == null || recording.getFileName().compareTo("") == 0)
							&& discardCallsWithourAudio) {
						log.debug("SKIPPING CALL without audio associated. URL: "
								+ recordingURL
								+ " DNIS: " + recording.getDnis() 
								+ " Date: " + recording.getDateTimeAsString());
						continue;
					}

					recordings.add(recording);
				} // for
			} // if
		}

		return recordings;
	}
	
	/**
	 * <p>
	 * Provides the name of the call recording file from call recording URL
	 * </p>
	 * <p>
	 * The URL looks like this:
	 * https://cdn.ringover.com/records/0-0/35537d8679f3e2-13-04-19-15h27-33600000000-33180800000.mp3
	 * </p>
	 * 
	 * @param url - URL to the call recording in mp3 format.
	 * @return The name of the call recording file
	 */
	private String getFileNameFromURL(String url) {
		String fileName = null;

		if (url != null) {
			String tokens[] = url.split("/");
			int size = tokens.length;

			fileName = tokens[size - 1];
		}

		return fileName;
	}
}
