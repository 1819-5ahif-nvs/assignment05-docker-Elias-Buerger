FROM ubuntu
EXPOSE 8080 9990

RUN apt-get update
RUN apt-get install -y mysql-client
RUN apt-get install -y openjdk-11-jdk

COPY target/microprofile-thorntail.jar /opt/microprofile/microprofile.jar
COPY wait.sh /opt/microprofile/wait.sh

ENV JAVA_TOOL_OPTIONS="-Djava.net.preferIPv4Stack=true -Djava.net.preferIPv4Addresses=true"

CMD ["/opt/microprofile/wait.sh"]