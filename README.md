#Status
**ON DEVELOPMENT**

#Introduction

Generic service to find best/nearby supplier associated to a bid. EasyMarket is platform to find best products offered by the suppliers. Customers select their list of products to be purchased through the application and this list and sent to different suppliers registered in EasyMarket. Best suppliers near by the customer are returned based on the best bid associated.

#Architecture

 1. This is a rest service implementation based on http://www.dropwizard.io framework which allow developing ops-friendly, high-performance, RESTful web services. It keep your app a simple, light-weight package that lets you focus on getting things done.
 2. It is integrated with documentation framework swagger https://github.com/swagger-api/swagger-core. Generating the Swagger API Specification, which enables easy access to your REST API. See screenshot below for more information. 
 3. Integrated with database H2. 

#How use?

##Running The Application

To start up rest services available, we need to run couple of command. To test the example application run the following commands:

 * Bulding artifacts, under the folder "easymarket-rest-service"

```
mvn clean install
```

 * Start services, loading easymarket rest services + database instance + rest documentation.  Under folder easymarket-rest-app:
```
java -jar target/easymarket-rest-app-0.0.1-SNAPSHOT.jar server example.yml
```

##How execute rest calls?

 You may have some possibilities: 
  1. Using existing integration tests
  2. Using REST swagger documentation generated. See screenshot below:
  ![POST Call](https://github.com/gonella/easymarket-rest-service/blob/master/easymarket-rest-service/docs/PostCallExampleViaSwagger.png "Rest documentation generated")

  3. Using CURL command 

##Executing Integration Tests

 1. You should guarantee your rest service is running. After that, see maven project under -> easymarket-rest-service -> utitilies -> easymarket-rest-client

```
See integration test classes under test folder: "org.easymarket.client.test.v1"
E.g. "ITSuppliersV1Test"
```

## REST documentation generated 

 1. Open documentation generated on runtime. http://localhost:8080/swagger	

![Documentation generated](https://github.com/gonella/easymarket-rest-service/blob/master/easymarket-rest-service/docs/EasymarketApiAvailableDoc.png "Rest documentation generated")

## Send manual rest calls

 * To post data into the application. Build your post data below.
```
curl -H "Content-Type: application/json" -X POST http://localhost:8080/people -d "{\"fullName\":\"Other Person\",\"jobTitle\":\"Other Title\"}"
```
 * Install CURL for Windows: http://www.confusedbycode.com/curl/

## To setup ONLY h2 database. Under folder easymarket-rest-app:

```
java -jar target/easymarket-rest-app-0.0.1-SNAPSHOT.jar db migrate example.yml
```

## More information about database used:

1. More information about dropwizard h2:
 * https://dropwizard.github.io/dropwizard/manual/migrations.html

##Continuos Integration

1. If you want to have continuos integration, see the options below:
 * https://drone.io/
 * https://www.snap-ci.com/

##TODO

 1. On progress

#More technical information: 

##Which micro service?

 1. Dropwizard X Spring Boot: 

```
https://dzone.com/articles/dropwizard-vs-spring-boot
http://clearthehaze.com/2014/07/migrating-from-spring-mvc-to-dropwizard/
```
