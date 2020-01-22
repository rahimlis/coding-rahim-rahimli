## Problem definition
Design and build a system that allows to receive and collect data about energy consumption from different villages. As a result, your system should, on-demand, give out the consumption report per village for the last 24h.

Consider that your system has an API that is called by electricity counters:

> POST /counter_callback
```json
{
  "counter_id": "1","amount": 10000.123
} 
```

To get information additional information about the counter you have to call the following external API:

> GET /counter?id=1
```json
{
  "id": "1","village_name": "Villarriba"
}
```

As a result, it's expected that your system will expose the following API:

> GET /consumption_report?duration=24h
```json
{
  "villages": [
    {
      "village_name": "Villarriba",
      "consumption": 12345.123
    },
    {
      "village_name": "Villabajo",
      "consumption": 23456.123
    }
  ]
}
```

## Solution 

![Architecture](/assets/Zenhomes.png)
##### Architecture
As it is clear from the architecture the application consists of two different pieces, one of which is responsible for storing records and retrieving information about counters, while the other part deals with reporting functionality.
##### Platform 
The outcoming solution is a spring boot application running on port 8080 by default. 
Spring boot is chosen to eliminate need for boilerplate code and speed up development. 
##### Testing
Service layer classes are tested with Spock framework which again boosts development speed, while web layer is tested using junit paired with spring runner.     
##### Data
For the sake of simplicity I have used in memory database H2, which is running while spring context is working. It means that once the application stops, all data will be lost. 
One can use following link to access database console: 
> localhost:8080/h2-console 

Database is migrated using liquibase (*/src/main/resources/liquibase*). Migration also includes some data population, which means that running application will already have some records in H2 memory. 
More info can be found in `db-changelog-1.0.sql`

##### App config
All application configuration is placed in application.yml file. Using spring profiles one can add different configurations for more in-depth testing. For example, integration testing can be done, provided that there are mock implementations for services. 