FROM registry.5173.com/public/alpine-oraclejdk8-bash:slim

ENV WORK_PATH /opt
ENV APP_NAME #(MVN_project_artifactId).jar

COPY $APP_NAME $WORK_PATH/

WORKDIR $WORK_PATH

# ENTRYPOINT
# ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom -server -Xms512m -Xmx512m -Xss256k -XX:+PrintGCDateStamps -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/var/log/stm/ -Xloggc:/var/log/stm/gc.log -XX:ErrorFile=/var/log/stm/hs_error.log"]
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom -server -Xms1024m -Xmx1024m -Xss256k"]

# CMD
CMD ["-jar", "#(MVN_project_artifactId).jar"]
