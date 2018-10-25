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


# Run

## Kubernetes

Kampuni is intended to run within Kubernetes. Deployment of latest 
release is done with:
```
kubernetes apply -f kubernetes-release.yml
```

Deployment of head of master is done with:
```
kubernetes apply -f kubernetes-latest.yml
```

The service is not exposed outside Kubernetes. Within the cluster it
can be accessed via `http://localhost:80/kampuni/v1/`

## Docker

It is possible to run directly in Docker using:

```
docker docker run -P --rm magru/kampuni:latest 
```

To access the service, in another window do:
```
curl --header "Accept: application/json" localhost:8080/kampuni/v1/
```


# Build

Head of master branch and release tags are automatically build 
by Azure automated builds.

To build locally: 

```
docker build .
```

To buld without having to use Docker and to do a faster build, one may use Googles jib. In 
this case one need maven installed on the machine.

```
mvn jib:build
```