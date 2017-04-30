# Sample Web-Service

To run the sample you will need:
 - Internet Connection (At least the first time it is run)
 - [Java 8 runtime](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
 - [Gradle 2.3+ ](https://gradle.org/install)
 
Once the repository is on your machine, in order to boot up the server do the following.

1. Open up a command window and make the current working directory the root of the web-service-sample repository
1. Run the command

        ./gradlew bootRun
    
Now there is a server running on your `localhost:8400` provided you did not have a process running on port 8400 before you started the server.