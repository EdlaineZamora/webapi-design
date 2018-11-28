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
* Graphql-java

## **API Paradigms**
### **REST (Representational State Transfer)**

This style is resource-based, where the URI is expressed by nouns and not verbs. It is also based on HTTP methods, where each verb has a different meaning than the API should respect. Below are some examples of requests.

For the examples, the Conference domain was used, where there are conferences that have a list of talks.

##### Getting All Conferences Endpoint
```javascript
GET /conferences/
Host: localhost:8080
```


##### Creating a Conference
```javascript
POST /conferences/
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
PUT /conferences/3
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
PATCH /conferences/3
Host: localhost:8080
Content-Type: application/json
{
    "name": "O'Reilly Conference"
}
```

##### Deleting a Conference
```javascript
DELETE /conferences/3
Host: localhost:8080
```

##### Getting All Talks from a Conference with Id 3
```javascript
GET /conferences/3/talks
Host: localhost:8080
```

### **RPC (Remote Procedure Call)**
The requests are action-based (unlike REST that is resource-based). This style is indicated for APIs that exposes many actions and that would be difficult to map with RESTs resources.

For this example, a conversation domain was used (like a Slack). Each conversation can undergo a series of commands (such as archive, close, exit, etc.).

Here you can find a good example of RPC-Style API:
[Slack RPC-Style API](https://api.slack.com/web)

And in this project you can find some simple examples of endpoints with the RPC-Style, as follows:

##### Getting All Conversations Endpoint
```javascript
GET /conversations/list
Host: localhost:8080
```

##### Getting Conversation by Id Endpoint
```javascript
GET /conversations/list?id=5
Host: localhost:8080
```

##### Archiving Conversation by Id Endpoint
```javascript
POST /conversations/list/archive?id=5
Host: localhost:8080
```

##### Closing Conversation by Id Endpoint
```javascript
POST /conversations/list/close?id=5
Host: localhost:8080
```

### **GraphQL**

Here you can find the GraphQL Java documentation for moer details.

[GraphQL-Java](https://www.graphql-java.com/documentation/)

