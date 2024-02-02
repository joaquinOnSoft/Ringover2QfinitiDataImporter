@ECHO OFF
CLS
ECHO.

REM ============================================================
REM Download all the calls of the current year 
REM using the Ringover API
REM
REM                       (C) OpenText 2023
REM ============================================================

SET JAVA="C:\Progra~1\Java\jdk-17.0.2\bin\java.exe"

SET JAR=Ringover2QfinitiDataImporter-24.02.03.jar

SET UNC_PATH=\\bcnfsox1\share_DVMAPL442$\Recordings
REM SET UNC_PATH=\\DVMAPL442\Recordings


REM `date` command returns a date like this: Tue 01/23/2024
REM set FROM_YEAR=%date:~10,4%
set FROM_YEAR=2023
set TO_YEAR=2023

REM set MONTHS=01 02 03 04 05 06 07 08 09 10 11 12
set MONTHS=02 03 04 05 06 07 08 09 10 11 12

for %%m in (%MONTHS%) do (
	CALL :downloadCalls %%m	ANSWERED
	CALL :downloadCalls %%m	OUT	
)

goto End


REM ============================================================
REM Download calls using Ringover API
REM @param %1 - Month of the year, i.e. 01, 02, 03...
REM @param %2 - Call type, i.e. ANSWERED, OUT
REM ============================================================
:downloadCalls
	set FROM_MONTH=%1
	set TYPE=%2
	
	set MONTLY_UNC_PATH=%UNC_PATH%\%TYPE%\%FROM_YEAR%\%FROM_MONTH%
	
	set FROM_DAY=01
	set TO_DAY=01
	
	call :setToMonth %1	
		
	echo Downloading %TYPE% calls from %FROM_YEAR%%FROM_MONTH%%FROM_DAY% to %TO_YEAR%%TO_MONTH%%TO_DAY%
	echo/
	@ECHO ON
	%JAVA% -Xmx2G -jar %JAR% --discard --wav --remove --from %FROM_YEAR%%FROM_MONTH%%FROM_DAY% --to %TO_YEAR%%TO_MONTH%%TO_DAY% --unc "%MONTLY_UNC_PATH%" -c %TYPE%  --output "%MONTLY_UNC_PATH%\%FROM_YEAR%-%FROM_MONTH%-calls-%TYPE%.xls" 
	@ECHO OFF
	goto :eof
	
	
		
REM ============================================================
REM Set Month duration in days
REM @param %1 - Month of the year, i.e. 01, 02, 03...
REM ============================================================
:setToMonth
	set MONTH=%1

	IF "%MONTH%"=="01" (set TO_MONTH=02)
	IF "%MONTH%"=="02" (set TO_MONTH=03)
	IF "%MONTH%"=="03" (set TO_MONTH=04)
	IF "%MONTH%"=="04" (set TO_MONTH=05)
	IF "%MONTH%"=="05" (set TO_MONTH=06)
	IF "%MONTH%"=="06" (set TO_MONTH=07)
	IF "%MONTH%"=="07" (set TO_MONTH=08)
	IF "%MONTH%"=="08" (set TO_MONTH=09)
	IF "%MONTH%"=="09" (set TO_MONTH=10)
	IF "%MONTH%"=="10" (set TO_MONTH=11)
	IF "%MONTH%"=="11" (set TO_MONTH=12)
	IF "%MONTH%"=="12" (set TO_MONTH=01& set /A TO_YEAR=%FROM_YEAR%+1)
	
	goto :eof	

:end