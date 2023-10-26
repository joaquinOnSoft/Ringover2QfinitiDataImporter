package com.opentext.qfiniti.importer.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

import org.junit.jupiter.api.Test;

public class FileUtilTest{
	private static final String RINGOVER_PROPERTIES = "ringover.properties";

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
		File f = FileUtil.getFileFromResources(RINGOVER_PROPERTIES);
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
	
	@Test
	public void filterFilesByExtension() {
		
		URL resource = getClass().getClassLoader().getResource(RINGOVER_PROPERTIES);
		if(resource != null) {			
			File[] files = FileUtil.filterFilesByExtension(resource.getPath().replace(RINGOVER_PROPERTIES, ""), ".properties");
			assertNotNull(files);
			assertEquals(1, files.length);
			assertEquals(RINGOVER_PROPERTIES, files[0].getName());
		}
		else {
			fail("Resource `ringover.properties` not found.");
		}
	}	
}