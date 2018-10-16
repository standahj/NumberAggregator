# Sample Solution
## Number Aggregator



Build:
```
mvn clean package
```

Run the command line version (from top directory):
```
 java -cp target/demo-aggregator-0.0.1-SNAPSHOT.jar.original com.example.demoaggregator.CommandLine
```

There is also a Web application option implemented as RESTful service with Spring Boot
To start web application, start the webs erver with maven command:
```
mvn spring-boot:run
```

and submit numbers to endpoint /addNumbers  with e.g. curl command:
```
curl 'http://localhost:8080/addNumbers' -H 'Pragma: no-cache' -H 'Origin: http://localhost' -H 'Content-Type: application/json' -H 'Accept: */*' --data-binaryumbers":"12,24,17"}'
```

The solution exhibits the Strategy design pattern where we can create a number of aggregators and number of formatters of the data,
and decide at runtime (or when selecting specific class to run - e.g as CLI app or as Web application), what the output format should be (HTML for Web, text for CLI)