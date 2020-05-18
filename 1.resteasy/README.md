# Step to deploy
1. login
```
oc login -u developer -p developer http://api.crc.testing:6443
```

2. new project
```
oc new-project demo-resteasy
```

3. maven build native package (requires docker)
```
mvn package -Pnative -Dquarkus.native.container-build=true
```

4. build container
```
docker build -f src/main/docker/Dockerfile.native -t quarkus/demo-resteasy .
docker images
```

5. deploy to OpenShift
```
oc new-build --binary --name=demo-resteasy -l app=demo-resteasy

oc patch bc/demo-resteasy -p "{\"spec\":{\"strategy\":{\"dockerStrategy\":{\"dockerfilePath\":\"src/main/docker/Dockerfile.native\"}}}}"

oc start-build demo-resteasy --from-dir=. --follow

oc new-app --image-stream=demo-resteasy:latest

oc expose service demo-resteasy
```



