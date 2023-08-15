FROM java:8
#开发者
MAINTAINER lx
#添加JAR包到容器，更名成file-service
ADD ./file-service-0.0.1-SNAPSHOT.jar  ./file-service.jar
#运行命令
ENTRYPOINT ["java","-jar","/file-service.jar"]
#暴露端口
EXPOSE 9000