CountriesApiApplication
This SpringBoot application gets the list of the countries based on the name, region, country code, capital city.

A country has fields like below:
name, alpha2Code, alpha3Code, callingCodes, capital, altSpellings, topLevelDomain, region, subregion, population, area, timezones, languages, translations, flag

Steps to Setup

Requires Java - 1.8.x, Maven - 3.x.x
**1. Configure H2DB Database

open src/main/resources/application.properties

change spring.datasource.url value for database as "jdbc:h2:mem:db;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE"

Data File country.json is added to src/main/resources/data

2. Build and run the app using maven

mvn clean install
java -jar target/restcountries 0.0.1-SNAPSHOT.jar```

Alternatively, you can run the app without packaging it using -

```bash
mvn spring-boot:run
The app will start running at http://localhost:8080

Explore GraphQL
The app defines following endpoints (GET Request)

Endpoint Name - HTTP Request Type

http://localhost:8080/rest/allcountries - List of all Countries

http://localhost:8080/rest/name/{name}?fullText=true - Countries list By Full Name

http://localhost:8080/rest/name/{name} - Countries list By Name

http://localhost:8080/rest/alpha/{code} - Countries list By Code

http://localhost:8080/rest/alpha?codes={codes} - Countries list By Mutiple Code

http://localhost:8080/rest/currency/{currency} - Countries list By Currency

http://localhost:8080/rest/lang/{language} - Countries list By Language

http://localhost:8080/rest/capital/{capital} - Countries list By Capital

http://localhost:8080/rest/region/{region} - Countries list by region

http://localhost:8080/rest/callingcode/{callingcode} - Countries list by callingcode

http://localhost:8080/rest/country - Create/add a new country

You can test them using postman.

Running the tests
To run the unit tests, call mvn test
