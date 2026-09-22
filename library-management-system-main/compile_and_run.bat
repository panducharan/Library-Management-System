@echo off
echo ===============================================
echo    LIBRARY MANAGEMENT SYSTEM - COMPILE & RUN
echo ===============================================
echo.

REM Create output directory
if not exist "build" mkdir build

echo 📁 Compiling Java files...
echo.

REM Compile all Java files
javac -d build src/*.java

if %ERRORLEVEL% EQU 0 (
    echo ✅ Compilation successful!
    echo.
    echo 🚀 Running Library Management System...
    echo.
    
    REM Run the application
    cd build
    java LibraryManagementApp
    cd ..
) else (
    echo ❌ Compilation failed! Please check the error messages above.
    echo.
)

echo.
echo Press any key to exit...
pause >nul
