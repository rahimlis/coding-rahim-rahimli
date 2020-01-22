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

As it is clear from the architecture the application consists of two different pieces, one of which is responsible for storing records and retrieving information about counters, while the other part deals with reporting functionality. The outcoming solution is a spring boot application running on port 8080 by default. 
Spring boot is chosen to eliminate need for boilerplate code and speed up development. Service layer classes are tested with Spock framework which again boosts development speed, while web layer is tested using junit paired with spring runner.     