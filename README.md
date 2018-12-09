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
### **Based in Request-Response**
#### **REST (Representational State Transfer)**

This style is resource-based, where the URI is expressed by nouns and not verbs. It is also based on HTTP methods, where each verb has a different meaning than the API should respect. Below are some examples of requests.

For the examples, the Conference domain was used, where there are conferences that have a list of talks.

###### Getting All Conferences Endpoint
```javascript
GET /conferences/
Host: localhost:8080
```


###### Creating a Conference
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

###### Fully updating a Conference
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

###### Partially updating a Conference
```javascript
PATCH /conferences/3
Host: localhost:8080
Content-Type: application/json
{
    "name": "O'Reilly Conference"
}
```

###### Deleting a Conference
```javascript
DELETE /conferences/3
Host: localhost:8080
```

###### Getting All Talks from a Conference with Id 3
```javascript
GET /conferences/3/talks
Host: localhost:8080
```

#### **RPC (Remote Procedure Call)**
The requests are action-based (unlike REST that is resource-based). This style is indicated for APIs that exposes many actions and that would be difficult to map with RESTs resources.

For this example, a conversation domain was used (like a Slack). Each conversation can undergo a series of commands (such as archive, close, exit, etc.).

Here you can find a good example of RPC-Style API:
[Slack RPC-Style API](https://api.slack.com/web)

And in this project you can find some simple examples of endpoints with the RPC-Style, as follows:

###### Getting All Conversations Endpoint
```javascript
GET /conversations/list
Host: localhost:8080
```

###### Getting Conversation by Id Endpoint
```javascript
GET /conversations/list?id=5
Host: localhost:8080
```

###### Archiving Conversation by Id Endpoint
```javascript
POST /conversations/list/archive?id=5
Host: localhost:8080
```

###### Closing Conversation by Id Endpoint
```javascript
POST /conversations/list/close?id=5
Host: localhost:8080
```

#### **GraphQL**

Here you can find the GraphQL Java documentation for more details.

[GraphQL-Java](https://www.graphql-java.com/documentation/)


#### **Comparison of request-response API paradigms**

|                   	|                                             REST                                             	| RPC                                                                                      	| GraphQL                                                                                                          	|
|-------------------	|:--------------------------------------------------------------------------------------------:	|------------------------------------------------------------------------------------------	|------------------------------------------------------------------------------------------------------------------	|
| What does  it do? 	| Expose data as resources and http methods to represent CRUD operations                       	| Expose action-based  API methods                                                         	| Expose a query language  for APIs where the clients define the structure of the response                         	|
| Examples          	| GitHub, Twitter, Google                                                                      	| Slack, Flickr                                                                            	| Facebook, GitHub                                                                                                 	|
| Usage             	| GET /conferences/<id> POST /conferences with payload                                         	| GET /conversations/list GET /conversations/list?id=1 POST /conversations/list/close?id=5 	| query ($id: String!) {     conferece(id: $id) {         name     } }                                             	|
| HTTP verbs        	| GET, POST, PUT, PATCH, DELETE, etc                                                           	| GET, POST                                                                                	| GET, POST                                                                                                        	|
| Advantages        	| Represented operations by HTTP Methods Easy to maintain Easy to implement Easy to understand 	| Easy to understand the actions Lightweight payloads High performance                     	| Saves multiple request-response  Avoids versioning Smaller payload Strongly typed                                	|
| Disadvantages     	| Big payloads Multiple request-response                                                       	|  Limited standardization Can lead to function explosion                                  	| Requires additional query parsing Backend performance optimization is difficult Too complicated for a simple API 	|
| When to use?      	| Recommended for API doing  CRUD like operations                                              	| Recommended for exposing multi-action APIs  that are difficult to represent with CRUD    	| Recommended for API which need a querying flexibility                                                            	|
