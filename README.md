#Twitter application (maven project) ![alt tag](https://travis-ci.org/jmg2014/twitter-app.svg?branch=master) <a href='https://coveralls.io/github/jmg2014/twitter-app?branch=master'><img src='https://coveralls.io/repos/github/jmg2014/twitter-app/badge.svg?branch=master' alt='Coverage Status' /></a>

###To compile the application:
mvn package

###To run the application:
inside the target folder: java -jar twitter-0.0.1.jar

###To run the unit tests:
mnv test 

###To run the integration tests:
mvn clean test-compile failsafe:integration-test failsafe:verify
