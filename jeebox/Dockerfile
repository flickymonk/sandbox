FROM tomcat:jdk17

COPY target/jeebox.war /usr/local/tomcat/webapps/jeebox.war

EXPOSE 8080
CMD ["catalina.sh", "run"]