# jdbidemo

Demonstration of using Jdbi in Spring Boot.

## Project Folders

### article folder
This folder contains an article for using Jdbi in Spring Boot to manage a database.

### docker folder
This folder contains a Docker compose file for launching a postgres instance to use with the demo application.

### rest folder
This folder contains a collection of REST calls for the open source Bruno REST testing tool. https://docs.usebruno.com

### sql folder
This folder has a couple sql files: clear.sql to clear the database of all data and the schema, and init.sql to initialize the database with sample data. It also has a dbml file with is a database markup definition of the database schema used in this demo. https://dbml.dbdiagram.io/home/

### src folder
The source code of the Jdbi demonstration application. The demo application project is managed with Maven.

## Running the Project

To stand up the database for the demo to use, run `docker compose up` (or similar tool) in the docker folder.

Once the database is running, the demo application can be started with `mvn spring-boot:run`

Call http://localhost:8080/api/v1/orders to list the orders. 

