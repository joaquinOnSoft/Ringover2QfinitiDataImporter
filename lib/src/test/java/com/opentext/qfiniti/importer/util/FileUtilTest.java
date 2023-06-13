package com.opentext.qfiniti.importer.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.Test;

public class FileUtilTest{
	@Test
	public void testDeleteFile() {
		String fileName = "filename.txt";
		FileWriter myWriter;
		try {
			myWriter = new FileWriter(fileName);
			myWriter.write("Files in Java might be tricky, but it is fun enough!");
			myWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			fail(e.getMessage());
		}

		assertTrue(FileUtil.isFile(fileName));
		FileUtil.deleteFile(fileName);
		assertFalse(FileUtil.isFile(fileName));
	}
	
	@Test
	public void testGetFileFromResources() {
		File f = FileUtil.getFileFromResources("user-mapping.properties");
		assertNotNull(f);
		assertTrue(f.exists());
	}
	
	
	@Test
	public void testIsFile() {
		String cwd = null;
		try {
			cwd = (new File( "." )).getCanonicalPath();
		} catch (IOException e) {
			fail(e.getMessage());
		}
		
		assertFalse(FileUtil.isFile(cwd));
	}
}