# Eremin Nikita coding assignment

## Build
This is a Maven project. To build call `mvn package` at project folder. Result is zip archive, containing folder with compiled jar and logging config.

## Run
Unzip folder and call `java -jar test-0.0.1-SNAPSHOT.jar`. App runs on built-in Apache Tomcat and H2 in-memory database.
App runs on port 8082.

To access database console, open http://localhost:8082/h2-console in web-browser.

Properties for login are
* JDBC URL: jdbc:h2:mem:dcbapp
* User Name: admin
* Password: password

Next to jar file `logs` folder will be created. It contains scheduled log of drones' battery levels.

## API
* registering a drone: POST localhost:8082/drones

    Input example:

    `{
        "serialNo": "FOOBAR123",
        "model": "CRUISERWEIGHT",
        "weightLimit": 400
    }`


* loading a drone with medication items: POST localhost:8082/drones/load/{droneId}
  
    Input example:

    `[
    {"medication":{"id": 2},"amount": 2},
    {"medication":{"id": 4},"amount": 1}
    ]`


* checking loaded medication items for a given drone: GET localhost:8082/drones/load/{droneId}


* checking available drones for loading: GET localhost:8082/drones/available


* check drone battery level for a given drone: GET localhost:8082/drones/battery/{droneId}