@echo off

cd %3

if exist ./%4 (
	java -jar %1 %2 ./%4
) else (
	echo no
)