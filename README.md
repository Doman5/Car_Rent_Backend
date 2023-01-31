# Car_Rent_Backend

### This is my car rent backend application.
### Application is made in the form of a rest api.
### I also create frontend app for this application: https://github.com/Doman5/Car_Rent_Frontend

## Technologie used
  - java 17
  - spring boot
  - spring JPA
  - spring mail
  - spring security
  - hibernate
  - liquibase
  - Mysql
  - jwt
  - here api (I decide to use here api because it have bigger free limits than google and i do not hook up a credit card)
  - lombok
  - common-io
  - swagger UI
  - spring test tools

## Functionalities for customer
  - login
  - registry
  - view available car
  - sorting cars by name, production year,body type and price
  - view users rent and edit user infos(first name, last name etc)
  - rent car
  - check car availability on selected dates
  
## Functionalities for admin
   - view, add, update and delete cars
   - upload car photos
   - view, add, update and delete car categories
   - view all rents with status change  
   - view all users and change user roles
   
## Another functionalities
   - basic security using spring security
   - jwt token authentication
   - checking photo names and seting avaliable
   - calcutate distane beetwen two location using here api
   - email sender with using adapter pattern
   - logged status changes for rents
 
 ## Future fuctionalities
  - cach functions
  - change car status(for example car is in repair) and blocking the possibility of rent car
  - adding opinion about customer
  - worker panel where worker can service rent(show todays rent, change stauses)
  
 ## How to start aplication
 
 ```
 mvn package
 java -jar carRent-0.0.1-SNAPSHOT.jar
 ```
 after this can you view documentation on http://localhost:8080/swagger-ui/index.html
  
   
