package com.opentext.qfiniti.importer.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.opentext.qfiniti.importer.pojo.CallRecording;

/**
 * SEE: https://www.callicoder.com/java-write-excel-file-apache-poi/
 * 
 * @author Joaquín Garzón
 */
public class ExcelWriter {

	public void write(String[] columns, List<CallRecording> recordings, String outputFileName)
			throws IOException, InvalidFormatException {
		// Create a Workbook
		// Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating
		// `.xls` file
		Workbook workbook = new HSSFWorkbook(); // new XSSFWorkbook() for generating `.xlsx` file

		/*
		 * CreationHelper helps us create instances of various things like DataFormat,
		 * Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way
		 */
		// CreationHelper createHelper = workbook.getCreationHelper();

		// Create a Sheet
		Sheet sheet = workbook.createSheet("Sheet1");

		// Create a Row
		Row headerRow = sheet.createRow(0);

		// Create cells
		for (int i = 0; i < columns.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columns[i]);
			// cell.setCellStyle(headerCellStyle);
		}

		// Create cell style to date cells
		CellStyle cellStyle = workbook.createCellStyle();
		CreationHelper createHelper = workbook.getCreationHelper();
		cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/mm/yyyy hh:mm:ss"));

		// Create Other rows and cells with employees data
		int rowNum = 1;
		int maxNumCol = columns.length;
		int col = 0;
		String columnName = null;
		
		Row row = null;
		Cell cell = null;
		String value = null;
		for (CallRecording recording : recordings) {
			row = sheet.createRow(rowNum++);

			for (int nCol = 0; nCol < maxNumCol; nCol++) {
				cell = row.createCell(col);

				value = null;
				columnName = columns[nCol];
				switch (columnName) {
				case CallRecording.HEADER_CALL_ID:
					//Intentionally empty
				case CallRecording.HEADER_CONNECTION_ID:
					value = recording.getId();
					break;
				case CallRecording.HEADER_PATH_NAME:
					value = recording.getPathName();
					break;
				case CallRecording.HEADER_DATE_TIME:
					cell.setCellValue(recording.getDateTime());
					cell.setCellStyle(cellStyle);
					value = null; //Avoid value assignment after the "case"	
					break;
				case CallRecording.HEADER_TEAM_MEMBER_NAME:
					value = recording.getTeamMemberName();
					break;
				case CallRecording.HEADER_DURATION:
					value = recording.getDurationAsString();
					break;
				case CallRecording.HEADER_GROUP_HIERARCHY:
					value = recording.getGroupHierachy();
					break;
				case CallRecording.HEADER_FILE_NAME:
					value = recording.getFileName();
					break;
				case CallRecording.HEADER_ANI:
					value = recording.getAni();
					break;
				case CallRecording.HEADER_DNIS:
					value = recording.getDnis();
					break;
				case CallRecording.HEADER_CALL_DIRECTION:
					value = Integer.toString(recording.getDirection());
					break;					
				case CallRecording.HEADER_USER_DATA:
					value = recording.getUserData();
					break;					
				default:
					value = recording.getExtendedField(columnName);
				}

				if (value != null) { 
					cell.setCellValue(value);	
				}				
				
				col++;
			}

			col = 0;
		}

		// Resize all columns to fit the content size
		for (int i = 0; i < columns.length; i++) {
			sheet.autoSizeColumn(i);
		}

		// Write the output to a file
		FileOutputStream fileOut = new FileOutputStream(outputFileName);
		workbook.write(fileOut);
		fileOut.close();

		// Closing the workbook
		workbook.close();
	}
}