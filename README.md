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
 ![alt text](https://user-images.githubusercontent.com/103491031/217057625-027b2289-4de7-499c-92de-992d7fe13a8b.png)
 ![alt text](https://user-images.githubusercontent.com/103491031/217057975-07dcc334-bb66-404b-8c75-085011c3928d.jpg)
 ![alt text]((https://user-images.githubusercontent.com/103491031/217058064-0737f409-0eac-4cde-b3e2-c67307d09051.jpg))
 ![alt text](https://user-images.githubusercontent.com/103491031/217058183-4fe01446-9f36-4eb5-b856-f7a67a4a5091.jpg)
 ![alt text](https://user-images.githubusercontent.com/103491031/217058204-22814f30-f851-4cb3-940d-0cd49e328f99.jpg)
  ![alt text](https://user-images.githubusercontent.com/103491031/217058208-63411369-a2b5-48b1-a2b2-0732c4dc8ba8.jpg)
   ![alt text](ttps://user-images.githubusercontent.com/103491031/217058210-588f4a34-1f44-4c1b-9a04-09bfeba55e1a.jpg)
    ![alt text](https://user-images.githubusercontent.com/103491031/217058217-8731aaf2-b2b2-45a8-9a0f-80d399e8c939.jpg)
    

