# Running The Application

To test the example application run the following commands.

* To run the server run.

        java -jar target/easymarket-rest-app-0.0.1-SNAPSHOT.jar server example.yml

* To hit the Hello World example (hit refresh a few times).

	http://localhost:8080/hello-world

* To post data into the application.


	Install CURL for Windows:
		http://www.confusedbycode.com/curl/


curl -H "Content-Type: application/json" -X POST http://localhost:8080/people -d "{\"fullName\":\"Other Person\",\"jobTitle\":\"Other Title\"}"
	
	open http://localhost:8080/people
	
	CHROME - REST 
		{"fullName":"Other Person3","jobTitle":"Other Title3"}

# Accessing REST doc generated 

  - http://localhost:8080/swagger	

  
#Build h2 database

* More information about dropwizard h2
	https://dropwizard.github.io/dropwizard/manual/migrations.html
	

* To package the example run.

        mvn package

* To setup the h2 database run.

        java -jar target/easymarket-rest-app-0.0.1-SNAPSHOT.jar db migrate example.yml
