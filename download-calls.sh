#!/bin/bash
clear


echo "      _                               ___        __ _       _ _   _  " 
echo "     (_)                             |__ \      / _(_)     (_) | (_) "
echo " _ __ _ _ __   __ _  _____   _____ _ __ ) |__ _| |_ _ _ __  _| |_ _  "
echo "| '__| | '_ \ / _` |/ _ \ \ / / _ \ '__/ // _` |  _| | '_ \| | __| | "
echo "| |  | | | | | (_| | (_) \ V /  __/ | / /| (_| | | | | | | | | |_| | "
echo "|_|  |_|_| |_|\__, |\___/ \_/ \___|_||____\__, |_| |_|_| |_|_|\__|_| "
echo "               __/ |                         | |                     "
echo "              |___/                          |_|                     "

JAR=lib/build/libs/Ringover2QfinitiDataImporter-23.10.jar
UNC=/c/Users/jgarzonpena/Downloads/Grupo-Planeta/OUT
CALL_TYPE=OUT

echo "Downloading calls from 1st quarter"
java -jar  $JAR --from 20230101 --to 20230131 --unc $UNC/01 -c $CALL_TYPE --discard
java -jar  $JAR --from 20230201 --to 20230228 --unc $UNC/02 -c $CALL_TYPE --discard
java -jar  $JAR --from 20230301 --to 20230331 --unc $UNC/03 -c $CALL_TYPE --discard

echo "Downloading calls from 2nd quarter"
java -jar  $JAR --from 20230401 --to 20230430 --unc $UNC/04 -c $CALL_TYPE --discard
java -jar  $JAR --from 20230501 --to 20230531 --unc $UNC/05 -c $CALL_TYPE --discard
java -jar  $JAR --from 20230601 --to 20230630 --unc $UNC/06 -c $CALL_TYPE --discard

echo "Downloading calls from 3rd quarter"
java -jar  $JAR --from 20230701 --to 20230731 --unc $UNC/07 -c $CALL_TYPE --discard
java -jar  $JAR --from 20230801 --to 20230831 --unc $UNC/08 -c $CALL_TYPE --discard
java -jar  $JAR --from 20230901 --to 20230930 --unc $UNC/09 -c $CALL_TYPE --discard

echo "Downloading calls from 4nd quarter"
java -jar  $JAR --from 20231001 --to 20231031 --unc $UNC/10 -c $CALL_TYPE --discard
java -jar  $JAR --from 20231101 --to 20231130 --unc $UNC/11 -c $CALL_TYPE --discard
java -jar  $JAR --from 20231201 --to 20231231 --unc $UNC/12 -c $CALL_TYPE --discard