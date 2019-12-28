#!/bin/bash
echo "-------删除容器---------"
docker stop wdk-code-general
docker rm wdk-code-general
docker rmi wdk-code-general:0.0.1
echo $(docker build -t wdk-code-general:0.0.1 .)
#echo "------请输入新的ContainerId------"
#read containerId --net=host
docker run --privileged  -d -p 8806:8806 --name wdk-code-general $(docker images | grep wdk-code-general |grep 0.0.1| awk '{print $3}')