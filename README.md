
**ON DEVELOPMENT**

#Introduction

Generic service to find best/nearby supplier associated to a bid. EasyMarket is platform to find best products offered by the suppliers. Customers select their list of products to be purchased through the application and this list and sent to different suppliers registered in EasyMarket. Best suppliers near by the customer are returned based on the best bid associated.

#Architecture



#How use?

##Running The Application

To test the example application run the following commands. To run the server:

Bulding artifacts

```
mvn clean install
```

Start services
```
java -jar target/easymarket-rest-app-0.0.1-SNAPSHOT.jar server example.yml
```

##Executing Integration Tests

 1. See maven project under -> easymarket-rest-service -> utitilies -> easymarket-rest-client

## REST documentation generated 

 1. Open documentation generated on runtime. http://localhost:8080/swagger	

![Documentation generated](https://github.com/gonella/easymarket-rest-service/blob/master/docs/EasymarketApiAvailableDoc.png "Rest documentation generated")


#TODO



