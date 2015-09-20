
**ON DEVELOPMENT**

#Introduction

Generic service to find best/nearby supplier associated to a bid. EasyMarket is platform to find best products offered by the suppliers. Customers select their list of products to be purchased through the application and this list and sent to different suppliers registered in EasyMarket. Best suppliers near by the customer are returned based on the best bid associated.

#Architecture



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

##Executing Integration Tests

 1. See maven project under -> easymarket-rest-service -> utitilies -> easymarket-rest-client

## REST documentation generated 

 1. Open documentation generated on runtime. http://localhost:8080/swagger	

![Documentation generated](https://github.com/gonella/easymarket-rest-service/blob/master/docs/EasymarketApiAvailableDoc.png "Rest documentation generated")

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

* More information about dropwizard h2,	https://dropwizard.github.io/dropwizard/manual/migrations.html

#TODO

#More technical information: 

##Which micro service?

	- Dropwizard X Spring Boot: 

```
https://dzone.com/articles/dropwizard-vs-spring-boot
http://clearthehaze.com/2014/07/migrating-from-spring-mvc-to-dropwizard/
```

