# webapi-design

This project has the function of exemplifying the different paradigms of a WebAPI (for example: REST, GraphQL, WebSocket, RPC, 
etc) and the good practices in creating a WebAPI design.

The project is still under construction and will be done incrementally.


## **Used Technologies**
* Java 8
* Maven
* Spring Boot
* Spring Web
* Spring Devtools
* H2 Database

## **API Paradigms applied in this project**
### **REST (Representational State Transfer)**

For the REST paradigm, the Conference domain was used, where there are conferences that have a list of talks.

##### Getting All Conferences Endpoint
```javascript
GET /conferences/ HTTP/1.1
Host: localhost
```


##### Creating a Conference
```javascript
POST /conferences/ HTTP/1.1
Host: localhost:8080
Content-Type: application/json
{
    "name": "O'Reilly",
    "talks": [
        {
            "name": "Designing APIs"
        }
    ]
}
```

##### Fully updating a Conference
```javascript
PUT /conferences/3 HTTP/1.1
Host: localhost:8080
Content-Type: application/json
{
    "name": "O'Reilly Conference",
    "talks": [
        {
            "name": "Designing APIs that developers love"
        },
        {
            "name": "Building Evolutionary Architecture"
        }
    ]
}
```

##### Partially updating a Conference
```javascript
PATCH /conferences/3 HTTP/1.1
Host: localhost:8080
Content-Type: application/json
{
    "name": "O'Reilly Conference"
}
```

##### Deleting a Conference
```javascript
DELETE /conferences/3 HTTP/1.1
Host: localhost:8080
```

##### Getting All Talks from a Conference with Id 3
```javascript
GET /conferences/3/talks HTTP/1.1
Host: localhost:8080
```

### **RPC (Remote Procedure Call)**
