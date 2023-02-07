# PaymentMethod with SpringBoot

## Introduction

Welcome to this PaymentMethod application, built using Java 8 and Spring Boot 2.7.8. 

## Purpose
 - CRUD PaymentMethods and PaymentPlans
 - Fetch by name,id

## Requirements

- Java 8
- Maven

## Database
- H2


### Table Of Endpoints 


 Endpoint              | Method | Params | Explanation                                      
-----------------------|--------|----|---------------------------------------------
/api/v1.0/configuration/payment-methods      | GET    | N/A |Get Payment Methods.                   
/api/v1.0/configuration/payment-methods   | GET    | name | Fetch Payment Method by Payment Method name. 
/api/v1.0/configuration/payment-methods/{id}  | GET    | N/A |  Payment Method by Id path variable.    
/api/v1.0/configuration/payment-methods  | GET    | id |  Get  payment method   by payment plan id 
/api/v1.0/configuration/payment-methods/{id}  | PUT    | id |  Update existing  payment method    
/api/v1.0/configuration/payment-methods  | POST   | N/A |  Create Payment method 
/api/v1.0/configuration/payment-methods{id}  | DELETE | N/A |  Delete a payment method by Id   





## Run Instructions

To run the application, follow these steps:

1. Clone the repository to your local machine
    - `$ git clone https://github.com/nabeeltariqbhatti/PaymentMethod.git`
2. Navigate to the project directory
    - `$ cd YOUR_REPO_NAME`
3. Build the application using Maven
    - `$ mvn clean install`
4. Run the application
    - `$ mvn spring-boot:run`



