#!/usr/bin/env bash
docker login --username=aly5173 registry.cn-hangzhou.aliyuncs.com
docker push registry.cn-hangzhou.aliyuncs.com/m5173/discovery-cluster:0.0.2-SNAPSHOT
