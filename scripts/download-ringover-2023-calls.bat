@ECHO OFF
CLS
ECHO.

REM ============================================================
REM Download all the calls of the current year 
REM using the Ringover API
REM
REM                       (C) OpenText 2023
REM ============================================================

SET JAR=Ringover2QfinitiDataImporter-24.01.25.jar

SET UNC_PATH=\\DVMAPL442\Recordings
REM SET UNC_PATH=\\DVMAPL442\Recordings\test


REM `date` command returns a date like this: Tue 01/23/2024
REM set YEAR=%date:~10,4%
set YEAR=2023
REM set MONTHS=01 02 03 04 05 06 07 08 09 10 11 12
set MONTHS=01 02 03 04 05 06
REM set MONTHS=01 02

for %%m in (%MONTHS%) do (
	CALL :downloadCalls %%m	ANSWERED
	REM CALL :downloadCalls %%m	OUT	
)

goto End


REM ============================================================
REM Download calls using Ringover API
REM @param %1 - Month of the year, i.e. 01, 02, 03...
REM @param %2 - Call type, i.e. ANSWERED, OUT
REM ============================================================
:downloadCalls
	set MONTH=%1
	set TYPE=%2
	
	set MONTLY_UNC_PATH=%UNC_PATH%\%TYPE%\%YEAR%\%MONTH%
	
	set FROM_DAY=01
	
	call :setMonthDuration %1	
		
	echo Downloading %TYPE% calls from %YEAR%%MONTH%%FROM_DAY% to %YEAR%%MONTH%%TO_DAY%
	echo/
	echo java -Xmx2G -jar %JAR% --discard --wav --remove --from %YEAR%%MONTH%%FROM_DAY% --to %YEAR%%MONTH%%TO_DAY% --unc "%MONTLY_UNC_PATH%" -c %TYPE%  --output "%MONTLY_UNC_PATH%\%YEAR%-%MONTH%-calls-%TYPE%.xls"
	echo/
	java -Xmx2G -jar %JAR% --discard --wav --remove --from %YEAR%%MONTH%%FROM_DAY% --to %YEAR%%MONTH%%TO_DAY% --unc "%MONTLY_UNC_PATH%" -c %TYPE%  --output "%MONTLY_UNC_PATH%\%YEAR%-%MONTH%-calls-%TYPE%.xls" 
	REM > nul 2>&1
	
	goto :eof
	
	
		
REM ============================================================
REM Set Month duration in days
REM @param %1 - Month of the year, i.e. 01, 02, 03...
REM ============================================================
:setMonthDuration
	set MONTH=%1
	
	IF "%MONTH%"=="01" (goto :set31DaysMonth) 
	IF "%MONTH%"=="03" (goto :set31DaysMonth) 
	IF "%MONTH%"=="05" (goto :set31DaysMonth) 
	IF "%MONTH%"=="07" (goto :set31DaysMonth) 
	IF "%MONTH%"=="08" (goto :set31DaysMonth) 
	IF "%MONTH%"=="10" (goto :set31DaysMonth) 
	IF "%MONTH%"=="12" (goto :set31DaysMonth) 
	IF "%MONTH%"=="02" (goto :set28DaysMonth) ELSE (goto :set30DaysMonth)	
	goto :eof
	

	
REM ============================================================
REM Set Month duration to 31 days
REM ============================================================
:set31DaysMonth
	set TO_DAY=31
	goto :eof


REM ============================================================
REM Set Month duration to 30 days
REM ============================================================

:set30DaysMonth
	set TO_DAY=30
	goto :eof
	
	

REM ============================================================
REM Set Month duration to 28 days
REM ============================================================	
:set28DaysMonth
	set TO_DAY=28
	goto :eof	
	
	
:end