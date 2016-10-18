FROM maven:3-jdk-8

MAINTAINER Paul_Ragot <paul.ragot@eisti.fr>

RUN mkdir -p /app

WORKDIR /app

ADD . /app

RUN rm -f /app/Dockerfile

RUN apt-get update && apt-get -y install tree && tree /app

RUN mvn install
