# theapp Project

## Running the application in dev mode

You can run the application in dev mode that enables live coding using:
```shell script
 mvn compile quarkus:dev
```
> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged to `quarkus-run.jar` using:
```shell script
./mvnw package
```

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

The application can be packaged to _über-jar_ using:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

## Uploading to GCP 

### Build the project 
```shell script
mvn clean package
```
or to skip tests
```shell script
mvn clean package -DskipTests -DskipIntegrationTests -U
```
 

### Submit to cloud build 
```shell script
gcloud builds submit --tag gcr.io/testk8s1/theapp  
``` 
## Launch application
```shell script
gcloud run deploy --image gcr.io/testk8s1/theapp  --platform managed
```