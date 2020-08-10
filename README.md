# spring-kafka-kerberos

This repo is a minimal example of how to use Kerberos authentication with Spring and Spring Kafka.

Once started the application will try to continuously send messages to Kafka, printing out every message it sends along with the new offset it receives on completion, until you abort it.


## How to configure and run

Build the application using `./gradlew clean bootJar`.

Create an `application.yml` or `application.properties` file.  There is only one mandatory setting you need to set:
* `spring.kafka.bootstrap-servers`

The following settings might not have suitable default for your use case, so change these as required (see code for defaults): 
* `spring.kafka.properties.sasl.jaas.config`
* `com.panaseer.demo.topic`
* `com.panaseer.demo.message-interval-millis`

Run the jar with:
```shell script
 java -jar spring-kafka-kerberos-<version>.jar --spring.config.location=application.yml  
``` 
