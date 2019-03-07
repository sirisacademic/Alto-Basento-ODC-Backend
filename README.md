# ALTO BASENTO API

Rest API build for the AltoBasento project.
It serves as a layer on top of a Virtuoso RDF database.

When the API is queried this REST service takes care of executing the corresponding 
SPARQL query.

A JSON / XML file compliant to the [OCS standard](http://standard.open-contracting.org/latest/en/) 
is then constructed based on the previously issued sparql query. 

## Getting started
### Prerequisites
To run this application Java 8 or greater is required 


### Installing 
Clone this repository, then build the application (for example using [Maven](https://maven.apache.org/))

You can then run the resulting .jar file with the standard command
`java -jar filename.jar`

## Built with
* [Spring boot](https://spring.io/projects/spring-boot) - A library used to build java applications
* [rdf4j](http://rdf4j.org/) - A library used to query a SPARQL endpoint with java
* [json](http://www.json.org/) - A library used to generate the JSON responses of the API
* [jackson-dataformat-xml](http://jackson.codehaus.org) - A library used to generate the XML responses of the API

## Usage
Once the application is started it runs at http://localhost:8080

### API

#### /tender 	Param: tenderId
Returns the tender associated with a particular ID. Example:
`http://localhost:8080/tender/6767364D6D`

#### /company 	Param: companyName
Returns all the tenders associated to a particular Company. Example:
`http://localhost:8080/company/Autolinee%20Caivano`

#### /all 
Returns all the tenders

## Authors

* **Cesare Grigoletto** - *Backend developer* - [Cesare Grigoletto](https://github.com/cece95)

## License

The MIT License (MIT)

Copyright (c) 2019 SIRIS Academic

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
