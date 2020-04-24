@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  gql startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Add default JVM options here. You can also use JAVA_OPTS and GQL_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto init

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:init
@rem Get command-line arguments, handling Windows variants

if not "%OS%" == "Windows_NT" goto win9xME_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\gql-1.0.0-SNAPSHOT.jar;%APP_HOME%\lib\vertx-web-graphql-3.9.0.jar;%APP_HOME%\lib\vertx-rx-java2-3.9.0.jar;%APP_HOME%\lib\vertx-web-3.9.0.jar;%APP_HOME%\lib\vertx-lang-kotlin-coroutines-3.9.0.jar;%APP_HOME%\lib\vertx-lang-kotlin-3.9.0.jar;%APP_HOME%\lib\graphql-java-14.0.jar;%APP_HOME%\lib\rxjava-2.2.12.jar;%APP_HOME%\lib\reactive-streams-1.0.3.jar;%APP_HOME%\lib\vertx-rx-gen-3.9.0.jar;%APP_HOME%\lib\vertx-web-common-3.9.0.jar;%APP_HOME%\lib\vertx-auth-common-3.9.0.jar;%APP_HOME%\lib\vertx-core-3.9.0.jar;%APP_HOME%\lib\vertx-bridge-common-3.9.0.jar;%APP_HOME%\lib\kotlinx-coroutines-core-1.2.1.jar;%APP_HOME%\lib\kotlin-stdlib-jdk8-1.3.30.jar;%APP_HOME%\lib\antlr4-runtime-4.7.2.jar;%APP_HOME%\lib\slf4j-api-1.7.25.jar;%APP_HOME%\lib\java-dataloader-2.2.3.jar;%APP_HOME%\lib\netty-handler-proxy-4.1.48.Final.jar;%APP_HOME%\lib\netty-codec-http2-4.1.48.Final.jar;%APP_HOME%\lib\netty-codec-http-4.1.48.Final.jar;%APP_HOME%\lib\netty-handler-4.1.48.Final.jar;%APP_HOME%\lib\netty-resolver-dns-4.1.48.Final.jar;%APP_HOME%\lib\netty-codec-socks-4.1.48.Final.jar;%APP_HOME%\lib\netty-codec-dns-4.1.48.Final.jar;%APP_HOME%\lib\netty-codec-4.1.48.Final.jar;%APP_HOME%\lib\netty-transport-4.1.48.Final.jar;%APP_HOME%\lib\netty-buffer-4.1.48.Final.jar;%APP_HOME%\lib\netty-resolver-4.1.48.Final.jar;%APP_HOME%\lib\netty-common-4.1.48.Final.jar;%APP_HOME%\lib\vertx-codegen-3.9.0.jar;%APP_HOME%\lib\jackson-databind-2.10.2.jar;%APP_HOME%\lib\jackson-core-2.10.2.jar;%APP_HOME%\lib\kotlin-stdlib-jdk7-1.3.30.jar;%APP_HOME%\lib\kotlin-stdlib-1.3.30.jar;%APP_HOME%\lib\kotlin-stdlib-common-1.3.30.jar;%APP_HOME%\lib\jackson-annotations-2.10.2.jar;%APP_HOME%\lib\mvel2-2.3.1.Final.jar;%APP_HOME%\lib\annotations-13.0.jar

@rem Execute gql
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %GQL_OPTS%  -classpath "%CLASSPATH%" io.vertx.core.Launcher %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable GQL_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%GQL_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
