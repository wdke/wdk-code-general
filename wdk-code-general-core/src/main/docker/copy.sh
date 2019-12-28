#!/bin/bash
echo "-------开始复制文件到指定文件目录---------"
cd auto-code/springboot-demo/
echo $(mvn clean)
echo $(mvn package)
rm -rf /opt/app/auto-code/springboot-demo/
mkdir /opt/app/auto-code/springboot-demo
cp ./target/springboot-demo-0.0.1-SNAPSHOT.jar /opt/app/auto-code/springboot-demo/
cp ./src/main/docker/* /opt/app/auto-code/springboot-demo/
cd /opt/app/auto-code/springboot-demo/
echo "-------开始部署docker容器---------"
echo $(docker-compose up -d)