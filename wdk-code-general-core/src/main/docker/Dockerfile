FROM java:8-jre-alpine
VOLUME /opt/app/auto-code /opt/app/auto-code
ADD wdk-code-general-core-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8806
ENV JAVA_OPTS="-Xms128m -Xmx128m"
#ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Dspring.profiles.active=integrated -Djava.security.egd=file:/dev/./urandom -jar /app.jar"]
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Dspring.profiles.active=dev -Djava.security.egd=file:/dev/./urandom -jar app.jar"]
