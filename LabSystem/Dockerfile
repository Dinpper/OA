FROM java:17-jdk-alpine
COPY *.jar /app.jar
CMD ["--server.port=8880"]
EXPOSE 8880
ENTRYPOINT ["java","-jar","/app.jar"]
