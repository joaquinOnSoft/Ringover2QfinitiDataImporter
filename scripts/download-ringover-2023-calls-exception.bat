@ECHO ON
cls

java -Xmx2G -jar Ringover2QfinitiDataImporter-24.02.03.jar --discard --wav --remove --from 20230101 --to 20230115 --unc "\\DVMAPL442\Recordings\OUT\2023\01" -c OUT  --output "\\DVMAPL442\Recordings\OUT\2023\01\2023-01-calls-OUT-a.xls"

java -Xmx2G -jar Ringover2QfinitiDataImporter-24.02.03.jar --discard --wav --remove --from 20230116 --to 20230131 --unc "\\DVMAPL442\Recordings\OUT\2023\01" -c OUT  --output "\\DVMAPL442\Recordings\OUT\2023\01\2023-01-calls-OUT-b.xls"


java -Xmx2G -jar Ringover2QfinitiDataImporter-24.02.03.jar --discard --wav --remove --from 20231201 --to 20231215 --unc "\\DVMAPL442\Recordings\OUT\2023\12" -c OUT  --output "\\DVMAPL442\Recordings\OUT\2023\12\2023-12-calls-OUT-a.xls"

java -Xmx2G -jar Ringover2QfinitiDataImporter-24.02.03.jar --discard --wav --remove --from 20231216 --to 20231231 --unc "\\DVMAPL442\Recordings\OUT\2023\12" -c OUT  --output "\\DVMAPL442\Recordings\OUT\2023\12\2023-12-calls-OUT-b.xls"