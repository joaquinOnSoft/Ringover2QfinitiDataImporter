package com.opentext.qfiniti.importer.ringover;

public class Ringover2QfinitiDataImporterLauncherTest {

	//@Test
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
