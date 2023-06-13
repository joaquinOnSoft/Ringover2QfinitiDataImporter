package com.opentext.qfiniti.importer.ringover;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.opentext.qfiniti.importer.ringover.pojo.calls.Call;
import com.opentext.qfiniti.importer.ringover.pojo.calls.TerminatedCalls;

public class RingoverAPIWrapperTest {

	private RingoverAPIWrapper wrapper;
	
	@BeforeEach
	public void beforeEach() {
		try {
			wrapper = new RingoverAPIWrapper();
		} catch (FileNotFoundException e) {
			fail(e);
		} catch (IOException e) {
			fail(e);
		}		
	}
	
	@Test
	public void getAllCalls() {
		TerminatedCalls calls = wrapper.getAllCalls();
		
		assertNotNull(calls);
		assertEquals(11330955, calls.getUserId());
		assertEquals(2, calls.getCallListCount());
		assertNotNull(calls.getCallList());
		List<Call>  callList = calls.getCallList();
		assertNotNull(callList.get(0).getUser());
		assertEquals("Garzón", callList.get(0).getUser().getFirstname());
		assertEquals("Joaquín", callList.get(0).getUser().getLastname());
	}
}
