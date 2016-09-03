@echo off

Set JAVA_HOME="C:\Program Files\Java\jdk1.8.0_40"
Set PROFILE="PROD"
Set APPLICATION_JAR=UIMediathque.jar
Set APPLICATION_LOCATION=D:\Programmes\Mediatheque
Set APPLICATION_CONFIG_LOCATION=%APPLICATION_LOCATION%\config

%JAVA_HOME%\bin\java.exe -Dspring.config.location=%APPLICATION_CONFIG_LOCATION% -Dspring.profiles.active=prod -jar "%APPLICATION_LOCATION%\%APPLICATION_JAR%"
