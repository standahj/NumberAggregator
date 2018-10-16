# Sample Solution
## Number Aggregator

The task:
Create a solution that takes a numbers on input, and calculates the minimum, maximum and average value.
There is a command line option (reading STDIN and writing to STDOUT), and Web - ready interface (RESTful service) exposing
a method to add numbers to overall tally and get the current Min, Max, Average

The solution exhibits the Strategy design pattern where we can create a number of aggregators and number of formatters of the data,
and decide at runtime (or when selecting specific class to run - e.g as CLI app or as Web application), what the output format should be (HTML for Web, text for CLI)

Build:
```
mvn clean package
```

Run the command line version (from top directory):
```
java -cp target/demo-aggregator-0.0.1-SNAPSHOT.jar.original com.example.demoaggregator.CommandLine
```

There is also a Web application option implemented as RESTful service with Spring Boot. 
To start web application, start the web server with maven command:
```
mvn spring-boot:run
```

There are two endpoints:

GET:  http://localhost:8080/getNumbers
   
   the above retrieves currentaggregator content (simple HTML page)
   
POST: http://localhost:8080/addNumbers

the /addNumbers  endpoint expects a payload with key 'numbers' and value is a comma-separated number list

Example payload:  '{"numbers":"-12,24.6,170"}'



You may submit numbers to endpoint /addNumbers  with e.g. curl command (or any other POST request generator):
```
curl 'http://localhost:8080/addNumbers' -H 'Pragma: no-cache' -H 'Origin: http://localhost' -H 'Content-Type: application/json' -H 'Accept: */*' --data-binary '{"numbers":"12,24,17"}'
```

The security on the endpoints is turned-off in order not to complicate the solution.  

