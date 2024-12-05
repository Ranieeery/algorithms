@echo off
pwsh -Command " 1..25 | ForEach-Object { $diaPath = '.\Dia {0:D2}' -f $_; New-Item -Path $diaPath -ItemType Directory; 1..2 | ForEach-Object {New-Item -Path (Join-Path -Path $diaPath -ChildPath ('Parte {0:D2}' -f $_)) -ItemType Directory}}"
