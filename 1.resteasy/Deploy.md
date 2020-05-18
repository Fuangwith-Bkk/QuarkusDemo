
//require docker
mvn package -Pnative -Dquarkus.native.container-build=true

docker build -f src/main/docker/Dockerfile.native -t quarkus/demo-resteasy .
docker images
oc new-project demo-resteasy

oc new-build --binary --name=demo-resteasy -l app=demo-resteasy

oc patch bc/demo-resteasy -p "{\"spec\":{\"strategy\":{\"dockerStrategy\":{\"dockerfilePath\":\"src/main/docker/Dockerfile.native\"}}}}"

oc start-build demo-resteasy --from-dir=. --follow

oc new-app --image-stream=demo-resteasy:latest

oc expose service demo-resteasy