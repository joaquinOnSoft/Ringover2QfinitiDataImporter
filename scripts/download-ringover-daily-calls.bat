@ECHO ONFF

CLS
ECHO.

REM ============================================================
REM Download all the calls, from yesterday, using 
REM the Ringover API
REM
REM                       (C) OpenText 2023
REM ============================================================


REM SET JAVA="C:\Program Files\Java\jdk-11.0.21\bin\java.exe"
SET JAVA="C:\Progra~1\Java\jdk-17.0.2\bin\java.exe"

SET JAR=C:\Ringover2QfinitiDataImporter\Ringover2QfinitiDataImporter-24.02.03.jar

REM SET UNC_PATH=\\DVMAPL442\Recordings
SET UNC_PATH=\\bcnfsox1\share_DVMAPL442$\Recordings

REM `date` command returns a date like this: Tue 01/23/2024
set YEAR=%date:~10,4%
set MONTH=%date:~7,2%
set DAY=%date:~4,2%

set TODAY=%YEAR%%MONTH%%DAY%

if exist %UNC_PATH% (
	SET TYPES=ANSWERED OUT	

	for %%t in (%TYPES%) do (
		echo %%t
				
		call :downloadYesterdayCall %%t
	)
)
goto :end


REM ============================================================
REM Validate if a folder exist, if not exist it'll be created
REM @param %1 - Folder path
REM ============================================================
:createDirIfNotExist
	set DIR=%1
	if not exist %DIR%\ (
		echo Creating %DIR%
		md %DIR% 			
	)

	goto :eof



REM ============================================================
REM Downlod yesterday calls using Ringover API
REM @param %1 - Call type, i.e. ANSWERED, OUT
REM ============================================================
:downloadYesterdayCall
	set TYPE=%1
	set DAILY_UNC_PATH=%UNC_PATH%\%TYPE%\%YEAR%\%TODAY%
	
	call :createDirIfNotExist %UNC_PATH%\%TYPE%
	
	call :createDirIfNotExist %UNC_PATH%\%TYPE%\%YEAR% 
	
	call :createDirIfNotExist %UNC_PATH%\%TYPE%\%YEAR%\%TODAY% 	
	
	echo %JAVA% -Xmx2G -jar %JAR% --discard --wav --remove --unc "%DAILY_UNC_PATH%" -c %TYPE%  --output "%DAILY_UNC_PATH%\%YEAR%%MONTH%%DAY%-calls-%TYPE%.xls"
	echo/
	%JAVA% -Xmx2G -jar %JAR% --discard --wav --remove --unc "%DAILY_UNC_PATH%" -c %TYPE%  --output "%DAILY_UNC_PATH%\%YEAR%%MONTH%%DAY%-calls-%TYPE%.xls" 
	REM > nul 2>&1
	
	goto :eof



:end



