@echo off
pwsh -Command "1..25 | ForEach-Object { New-Item -Path ('.\Day_{0:D2}' -f $_) -ItemType Directory }"
pause