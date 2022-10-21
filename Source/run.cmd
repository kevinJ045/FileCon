@echo off

set "classpathlib=-cp "bin;lib/*""

if "%1"=="-b" (
	javac %classpathlib% src/*
)

if "%1"=="-c" (
	javac %classpathlib% src/%2.java
)

if "%1"=="-s" (
	java %classpathlib% src/%2.java
)

if not "%3"=="-s" (
	java %classpathlib% src/com/filecon/MainActivity
)
