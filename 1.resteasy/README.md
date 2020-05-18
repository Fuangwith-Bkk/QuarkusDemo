# demo-resteasy project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```
./mvnw quarkus:dev
```

## Packaging and running the application

The application can be packaged using `./mvnw package`.
It produces the `demo-resteasy-1.0.0-SNAPSHOT-runner.jar` file in the `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

The application is now runnable using `java -jar target/demo-resteasy-1.0.0-SNAPSHOT-runner.jar`.

## Creating a native executable

You can create a native executable using: `./mvnw package -Pnative`.

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: `./mvnw package -Pnative -Dquarkus.native.container-build=true`.

You can then execute your native executable with: `./target/demo-resteasy-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/building-native-image.



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



