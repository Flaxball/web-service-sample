# Sample Web-Service

To run the sample you will need:
 - Internet Connection (At least the first time it is run)
 - [Java 8 runtime](http://blog.acari.io/jvm/2017/05/05/Gradle-Install.html)
 - [Gradle 2.3+ ](http://blog.acari.io/jvm/2017/05/05/Gradle-Install.html)
 
Once the repository is on your machine, in order to boot up the server do the following.

1. Open up a command window and make the current working directory the root of the web-service-sample repository
1. Run the command

        ./gradlew bootRun
    
Now there is a server running on your `localhost:8400` provided you did not have a process running on port 8400 before you started the server.

To view the WSDL, hit the following endpoint:

    http://localhost:8400/computer-service/computers.wsdl