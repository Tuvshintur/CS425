# SMT
SE - SMT Project


1. smt-docs /Documents of project/
2. smt-backend /spring boot project/
3. smt-frontend-angular /Angular project/
4. smt-frontend-react /we decied to implement angular/



For initializing 

1. Java 1.8
2. MySQL 8.0 or later
3. Postman for sending request and getting response
4. Intellij IDEA ultimate /student evaluation/


For create working environment

1. Open Intellij IDEA import stm-backend as a project /IDE will download gradle and building project environment automatically/
2. Open mysql console and create database named "smt"
3. Go into project smt-backend, inside of application.properties find property named spring.datasource.password=YOUR DB's ROOT PASSWORD
4. Start project from Intellij IDEA, it will start server on 8080 port
5. Signup to Postman and give registered e-mail to get access to Postman's team workspace that stores request and response example


For deployment environment

1. Install MySQL 8 server
2. run dump file located in /GITHUB DIRECTORY/smt-deployment/dbscript.sql
3. create user on MySQL
    3.1. username : smtuser
    3.2. password : smtuser
    3.3. set priviligies to user 
3. Install Tomcat 8 server
4. deploy /GITHUB DIRECTORY/smt-deployment/smt.war to Tomcat server