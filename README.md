<a href="https://github.com/jmg2014/twitter-app/blob/master/LICENSE"><img src="https://img.shields.io/badge/License-Apache%202.0-blue.svg" alt="License"></a>
![alt tag](https://travis-ci.org/jmg2014/twitter-app.svg?branch=master) <a href='https://coveralls.io/github/jmg2014/twitter-app?branch=master'><img src='https://coveralls.io/repos/github/jmg2014/twitter-app/badge.svg?branch=master' alt='Coverage Status' /></a>
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/472d62204e35482bb22497f2f0eac94f)](https://www.codacy.com/app/jormangon/twitter-app?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=jmg2014/twitter-app&amp;utm_campaign=Badge_Grade)

#Twitter application (maven project)

###To compile the application:
mvn package

###To run the application:
inside the target folder: java -jar twitter-0.0.1.jar

###To run the unit tests:
mnv test 

###To run the integration tests:
mvn clean test-compile failsafe:integration-test failsafe:verify
