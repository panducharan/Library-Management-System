@echo off
echo ===============================================
echo    LIBRARY MANAGEMENT SYSTEM - QUICK RUN
echo ===============================================
echo.

REM Check if build directory exists
if not exist "build" (
    echo 📁 Build directory not found. Compiling first...
    call compile_and_run.bat
) else (
    echo 🚀 Running Library Management System...
    echo.
    
    REM Run the application
    cd build
    java LibraryManagementApp
    cd ..
)

echo.
echo Press any key to exit...
pause >nul
