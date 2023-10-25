package com.opentext.qfiniti.importer.util;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class FfmpegHelperTest {

	@Test
	public void isFfmpegInPath() {
		assertTrue(FfmpegHelper.isFfmpegInPath());
	}
}
