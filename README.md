# Shortener Url

This project is responsible to shorten urls and also resolve them.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

To execute the project on you machine you'll need: 
- Java 8  
- Docker
- Some IDE (intellij, eclipse, etc)
- Activate annotation processor to use lombok

### Installing

A step by step series of examples that tell you how to get a development env running

To execute the project, build the project:

```
./gradlew clean build docker
```

And run the command below:

```
docker run -p 8080:8080 -t com.vanhack/shortener 
```

After that, you should see:

```
Tomcat started on port(s): 8080 (http) with context path
```

An example of post request:

```
curl -X POST "http://localhost:8080/?url=http%3A%2F%2Fwww.google.com.br" -H "accept: */*"
```

It's will return:
```
http://localhost:8080/XtKCPR
```

## Running the tests

To execute automated tests, execute the command below:
```
./gradlew test
```

## Configurations
- In application.yml file, you must configure the application url, that must be the same of you application running,
for example, if you are running your application on the following DNS "www.shortener.com", the property application.url,
 must be "www.shortener.com"

## Author
* **Rodrigo Chibana**
