# cs4389-encryption-library
Class project for CS 4389 - Data and Applications Security

To execute from deployment:
Our project is currenly deployed using Heroku. To use the application, simply visit https://cyberchaser.herokuapp.com/swagger-ui.html (it might take a while to load. If it doesn't load after a few refreshes, please follow the instructions below.)

To execute locally:
1. Make sure port 8080 is available. Open the project in an editor or IDE. (If maven dependencies are not loading, make sure the pom.xml is registered as a maven project file.)
2. Through the IDE/Editor's built in terminal, execute the commands below.
3. For Mac users: After installing maven locally using $ brew install maven, execute
  
  $ mvn spring-boot:run
  
   For PC users: execute
   
  $ ./mvnw.cmd spring-boot:run

4. Enter http://localhost:8080/swagger-ui.html# into your browser.
5. Use the various encryption/decryption controller functions and hashing functions to encrypt/decrypt text and use the various file manipulation methods to upload/download files from the cloud.

There are no specific OS requirements to execute this code.

Just make sure you have a Java JDK and IntelliJ. Depending on your JDK version you may have to change your dependencies. IntelliJ should give error if it's not the right version and then switch for you automatically after you agree.

Just building the project and running is sufficient for executing the code.

Please email art160630@utdallas.edu if you have any questions.
