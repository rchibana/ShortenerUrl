# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
# Run the jar file
ENTRYPOINT ["java","-cp","app:app/lib/*","com.vanhack.shortener.ShortenerApplication"]