This project is archived. Systembolaget have changed its way
making data avaliable which make this repo invalid.

Kampuni is a simple REST service providing the assortment of
the Swedish alcohol monopoly stores Systembolaget. It cache
the assortment once a day from files published by Systembolaget.
Some cleaning of the data is done during import.

# Disclaimer

Kampuni and its development is not in any way affiliated with
Systembolaget. Systembolaget can not be held responsible for the
function nor content published by Kampuni.

Usage of Kampuni most follow regulation regarding
alcohol etc for the country where it is used.  


# Build & Run

## Docker
Build a container and run with Docker:
```
./mvnw clean package -Dquarkus.container-image.build=true
docker run -p 8080:8080 kampuni:devel
```
To access the service, in another window do:
```
curl --header "Accept: application/json" localhost:8080/v1/
```

## Kubernetes
If you instead want to run on a local Minikube:
```
eval $(minikube -p minikube docker-env)
./mvnw clean package -Dquarkus.container-image.build=true
kubectl apply -f kubernetes-devel.yml
```
You can access the service via the following command:
`curl -i --header "Accept: application/json" $(minikube ip):$(kubectl get service kampuni -o jsonpath='{.spec.ports[0].nodePort}')/v1/`

## Plain Java
Build a fat jar and run with Java without containers.
```
./mvnw clean package
java -jar target/kampuni-1.0.0-SNAPSHOT-runner.jar
```
To access the service, in another window do:
```
curl --header "Accept: application/json" localhost:8080/v1/
```
