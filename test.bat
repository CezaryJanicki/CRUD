call runcrud.bat
if "ERRORLEVEL" == "0" goto openWebsite
goto fail

:openWebsite
start chrome --new-window http://localhost:8080/crud/tasks
echo Everything is OK
goto end

:fail
echo.
echo There were errors
echo Cannot open file: "runcrud.bat"

:end
echo.
echo Work is finish
read
