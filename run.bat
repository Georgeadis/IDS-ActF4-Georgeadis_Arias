@echo off
REM Script para compilar y ejecutar el proyecto desde CMD
cd /d "%~dp0src"
if not exist "..\bin" mkdir "..\bin"
javac -d "..\bin" *.java
if errorlevel 1 (
  echo Compilación fallida. Revise los errores.
  exit /b 1
)
echo Ejecutando Main...
java -cp "..\bin" Main
