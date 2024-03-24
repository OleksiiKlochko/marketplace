# A simple example of using Spring Modulith

This is the author's attempt to learn how to use [Spring Modulith](https://docs.spring.io/spring-modulith/reference/) in Spring Boot applications. Also inspired by the article [Introduction to Spring Modulith](https://www.baeldung.com/spring-modulith).

#### Requirements

- [Java 21+](https://www.java.com/)
- [Maven 3.9.x+](https://maven.apache.org/)
- [Docker 25+](https://www.docker.com/)

#### Build

`mvn clean install`

#### Run

`mvn spring-boot:run`

## Modules

One way to check the structure of modules is to use the provided Actuator endpoint http://localhost:8080/actuator/modulith. It looks like this:

```json
{
  "product": {
    "displayName": "Product",
    "basePackage": "com.example.marketplace.product",
    "dependencies": []
  },
  "order": {
    "displayName": "Order",
    "basePackage": "com.example.marketplace.order",
    "dependencies": [
      {
        "target": "product",
        "types": [
          "USES_COMPONENT"
        ]
      }
    ]
  }
}
```
