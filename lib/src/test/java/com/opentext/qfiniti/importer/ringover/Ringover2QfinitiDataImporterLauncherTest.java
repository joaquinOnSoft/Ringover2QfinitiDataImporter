package com.opentext.qfiniti.importer.ringover;

import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import com.opentext.qfiniti.importer.pojo.CallRecording;

public class Ringover2QfinitiDataImporterLauncherTest {
	
	private static RingoverAPIWrapper wrapper;
	
	private static List<CallRecording> calls = null;
	
	@Before
	public void setUp() {	
		// Initialize fake Ringover response
		calls = new LinkedList<CallRecording>();
		
		CallRecording call1 = new CallRecording();
		call1.setAni("1234");
		// "dd/MM/yyyy HH:mm:ss"
		call1.setDateTime("01/06/2023 16:15:25");
		call1.setDnis("987654321");
		call1.setDuration(57);
		call1.setFileName("call-987654321.wav");
		call1.setGroupHierachy("GROUP-TEST");
		call1.setPathName("\\\\\\MY-SERVER\\calls");
		call1.setTeamMemberName("Richards, Reed");
		
		CallRecording call2 = new CallRecording();
		call2.setAni("5678");
		// "dd/MM/yyyy HH:mm:ss"
		call2.setDateTime("01/06/2023 18:38:44");
		call2.setDnis("99988777");
		call2.setDuration(129);
		call2.setFileName("call-998776552.wav");
		call2.setGroupHierachy("GROUP-TEST");
		call2.setPathName("\\\\\\MY-SERVER\\calls");
		call2.setTeamMemberName("Storm, Susan");		
		
		calls.add(call1);
		calls.add(call2);
		
		
		// Initialize Ringover mockup response
		wrapper = mock(RingoverAPIWrapper.class);
		try {
			when(wrapper.getAllCalls(any(Date.class), any(Date.class), any(CallType.class))).thenReturn(calls);
		} catch (IllegalArgumentException | IOException e) {
			fail(e.getMessage());
		}	
	}	
	
	@Test
	public void main() {
		String[] args = {
				"--from", "20230601",
				"--to", "20230615",
				"--callType", "ANSWERED",
				"--unc", "\\\\MY-SERVER\\calls"
		};
		
		Ringover2QfinitiDataImporterLauncher.main(args);
	}
}
