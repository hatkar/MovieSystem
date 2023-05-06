#Stage 1
# initialize build and set base image for first stage
#FROM maven:3.6.1-jdk-8-alpine as stage1
# speed up Maven JVM a bit
#ENV MAVEN_OPTS="-XX:+TieredCompilation -XX:TieredStopAtLevel=1"
# set working directory
#WORKDIR application
# copy  pom.xml and settings.xml files to the container
#COPY pom.xml .

##COPY settings.xml /usr/share/maven/conf/settings.xml
# go-offline using the pom.xml
#RUN mvn dependency:go-offline
# copy your other files
#COPY ./src ./src
# compile the source code and package it in a jar file
#RUN mvn clean install -P docker -Dmaven.test.skip=true

#Stage 2
# set base image for second stage
FROM adoptopenjdk/openjdk11:alpine-jre as builder
# First step : Extract the layers
WORKDIR application
ARG JAR_FILE=target/*.jar
#COPY --from=stage1 ${JAR_FILE} application.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract
FROM adoptopenjdk/openjdk11:alpine-jre as runtime
# Second step : Copy the extracted layers
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
CMD ["java", "org.springframework.boot.loader.JarLauncher"]
