FROM openjdk:8-slim
MAINTAINER "Ushahemba Ukange <ukange.ushahemba@gmail.com>"

# Define working directory.
WORKDIR /work
ADD target/test-pipeline-0.0.1-SNAPSHOT.jar /work/test-pipeline-0.0.1-SNAPSHOT.jar

ARG JAVA_OPTS

ENV JAVA_OPTS -Xmx512m
# Expose Ports
EXPOSE 8080
#ENTRYPOINT exec java -Djavax.net.ssl.trustStore=/mnt/devdir/cert-truststore.jks -Djavax.net.debug=ssl,handshake $JAVA_OPTS -jar /work/credit-transfer-gateway-0.0.1-SNAPSHOT.jar --spring.config.location=/properties/application.properties
ENTRYPOINT exec java $JAVA_OPTS -jar /work/test-pipeline-0.0.1-SNAPSHOT.jar --spring.config.location=/properties/application.properties