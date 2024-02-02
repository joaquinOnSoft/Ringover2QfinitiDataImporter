@echo on

cls

SET JAVA="C:\Progra~1\Java\jdk-17.0.2\bin\java.exe"

SET UNC_PATH=\\bcnfsox1\share_DVMAPL442$\Recordings\OUT\2023

REM %JAVA% -Xmx2G -jar Ringover2QfinitiDataImporter-24.02.03.jar --discard --wav --remove -c OUT --from 20230101 --to 20240101 --unc "%UNC_PATH%" --output "%UNC_PATH%\2023-calls-OUT.xls"

REM %JAVA% -Xmx2G -jar Ringover2QfinitiDataImporter-24.02.03.jar --discard --wav --remove -c OUT --unc "%UNC_PATH%" --output "%UNC_PATH%\2023-calls-OUT.xls"


SET UNC_PATH=\\bcnfsox1\share_DVMAPL442$\Recordings\ANSWERED\2023

%JAVA% -Xmx2G -jar Ringover2QfinitiDataImporter-24.02.03.jar --discard --wav --remove -c ANSWERED --from 20230101 --to 20240101 --unc "%UNC_PATH%" --output "%UNC_PATH%\2023-calls-OUT.xls"