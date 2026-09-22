@echo off
echo ╔══════════════════════════════════════════════════════════╗
echo ║            🏛️ LIBRARY MANAGEMENT SYSTEM 🏛️             ║
echo ╚══════════════════════════════════════════════════════════╝
echo.
echo ┌────────────────────────────────────────────────┐
echo │         📋 OPTION 2: MEMBER MANAGEMENT        │
echo └────────────────────────────────────────────────┘
echo.

echo Compiling and running Member Management Demo...
echo.

javac MemberDemo.java
if %ERRORLEVEL% EQU 0 (
    java MemberDemo
) else (
    echo ❌ Compilation failed!
)

echo.
echo Press any key to exit...
pause >nul
