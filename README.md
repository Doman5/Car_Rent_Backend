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
 
 you muss have created database named rentcars working on port 3306 
 ```
 mvn package
 java -jar carRent-0.0.1-SNAPSHOT.jar
 ```
 after this can you view documentation on http://localhost:8080/swagger-ui/index.html
 
 ## Screen shots
 ![alt text]([https://user-images.githubusercontent.com/103491031/217057625-027b2289-4de7-499c-92de-992d7fe13a8b.png])
 ![alt text](https://iv.pl/image/GZGpuv7)
 ![alt text](https://zapodaj.net/17e2afd882bb0.jpg.html)
 ![alt text](https://zapodaj.net/83f0af62bb272.jpg.html)
 ![alt text](https://zapodaj.net/f103369b8b327.jpg.html)
  
  https://iv.pl/image/GZGqf4b
https://iv.pl/image/GZGpuv7
https://iv.pl/image/GZGq5I5
https://iv.pl/image/GZGpx9D
https://iv.pl/image/GZGpsnp
https://iv.pl/image/GZGpTTe
https://iv.pl/image/GZGpIDI
https://iv.pl/image/GZGpZq2
https://iv.pl/image/GZGpdXY
   
