From openjdk:8u222-slim

RUN apt-get update -y \
    && apt-get install curl -y \
    && useradd ctf \
    && mkdir /opt/app

COPY buggyloader.jar /opt/app
COPY flag /flag

WORKDIR /opt/app

EXPOSE 8080

USER ctf
CMD ["java", "-jar", "/opt/app/buggyloader.jar"]
