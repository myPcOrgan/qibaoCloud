# Version 0.0.1
FROM registry.cn-hangzhou.aliyuncs.com/m5173/alpine-oraclejdk8-bash:slim

MAINTAINER Lex Meng "menglm@5173.com"

# 环境变量
ENV WORK_PATH /opt
ENV APP_NAME @project.build.finalName@.@project.packaging@
ENV APP_VERSION @project.version@
ENV IMAGE_NAME @imageName@

#EXPOSE 8761

#USER
#USER user:group

#VOLUME
VOLUME ["/var/log/stm"]

#ADD

#COPY
COPY $APP_NAME $WORK_PATH/

#LABEL
#STOPSIGNAL
#ARG
#ONBUILD

# WORKDIR
WORKDIR $WORK_PATH


# ENTRYPOINT
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom -server -Xms512m -Xmx512m -Xss256k -XX:+PrintGCDateStamps -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/var/log/stm/ -Xloggc:/var/log/stm/gc.log -XX:ErrorFile=/var/log/stm/hs_error.log "]

# CMD
CMD ["-jar", "@project.build.finalName@.@project.packaging@"]
