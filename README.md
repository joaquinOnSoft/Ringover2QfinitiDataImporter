# Ringover2QfinitiDataImporter

Command line tool to download calls from `Ringover` and generate an Excel file that can be used as input of `Qfiniti Data Importer`

> **Ringover** is a 100% Cloud voice - video - chat - text and call center solution.

> **OpenText Qfiniti** is a call center workforce management software that helps 
> analyze agent to customer multichannel interactions to improve customer service.

> The `Qfiniti Data Import tool`  is used to import recording transactions from etalk Recorder, 
> older versions of Qfiniti, or a 3rd party recording device into Qfiniti.

## Command line execution

To run this command line tool you just need to execute runable jar, i.e.

```script
java -jar Ringover2QfinitiDataImporter23.06.jar -from 20230601 -to 20230621 -c ANSWERED
```

### Valid arguments

These are the valid arguments admitted by this tool:
         
 - **-c,--callType <arg>**   (OPTIONAL) Call type. Used to filter certain types of call. Default value `ANSWERED`. Possible values:
    - *ANSWERED*:  filters answered calls. Default value.
    - *MISSED*:    filters missed calls.
    - *OUT*:       filters outgoing calls.
    - *VOICEMAIL*: filters calls ending on voicemail.
 - **-f,--from <arg>**       (OPTIONAL) From date. Format: yyyymmdd. Default value: yesterday
 - **-o,--output <arg>**     (OPTIONAL) Output file name. Default value: calls-yyyyMMdd.xls
 - **-t,--to <arg>**         (OPTIONAL) To date. Format: yyyymmdd. Default value: today
 - **-u,--unc <arg>**        (MADATORY) Universal Naming Convention (UNC) path to store the call recording files, i.e. *\\SERVER\recordings*
 
### Output

In case of success, the tool will generate an Excel file, by default called `calls-yyyyMMdd.xls`, that looks like this:

| Path_Name	        | File_Name                   | Date_Time           | duration	| group_hierarchy | Team_Member_Name  | dnis         | 
|-------------------|-----------------------------|---------------------|-----------|-----------------|-------------------|--------------|
| \\MY-SERVER\calls	| 33600000000-33180800000.mp3 | 23/06/2023 09:50:42 | 22        | 9161459         | Tonioni, Federico |	346789456123 |

 This file can be used as input of `Qfiniti Data Import tool` in order to ingest the calls into **OpenText Explore**.