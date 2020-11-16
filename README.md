# fullstack
All fullstack assignments , involving Java, Spring Boot, NodeJs, ReactJS, AngularJS code repos




There are 3 separate repos -

1) course_management (Server side)
  - To run locally,Below is the pre-requisites
      -- Java 9
	  -- Maven
	  -- Locally,I have used (STS). But, anyway you can be able to run this app.
	  
  - Internet is required.Database is on cloud (PostgreSQL tiny instanceprovider). it's a free instance, so it have
    some limitations, like number of DB connections.
  - spring boot + webflux 
  
  - Please run application as a Spring Boot, to ensure all requisite artifacts got generated. (OPTIONAL)
    
  - Service starting on port 8080.(Please ensure it is open).
  
  - Backend Testcases (Mock approach based) are present under test directory. 
    Test class can be run as a JUnit class.
	Please note, all required artifacts (classes) got generated. Generally, 
	Spring Boot run ensures all  artifacts got created in target directory.

2) react-frontend (Client side)
  - NodeJS 14.x
  - npm install (to load all dependencies) 
  - npm start 
  
  - Frontend Testcases (Jest & Enzyme  based) are present under component directory, files having
    naming pattern with .test prior .js extension. 
    Test suites can be executed with npm test command.
  
   
3) course_webtest (Integration Testcases - Selenium + Headless Chrome)
  - To run locally,Below is the pre-requisites
      - Java 9
	  - Maven
	  - Locally,I have used (STS). But, anyway you can be able to run this app.
	  
  - To try out testcases, please ensure (frontend and backend servers are up and running).

  - There is only one class, and it should be run as a Java Application.  
