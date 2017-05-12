# ING ATM Locator service.

### Overview
a RESTful Service to locate ING ATMs in a Dutch City  

###Features:
•	Application exposes an rest end-point to fetch the list of ING ATMs in specified city.
•	Application uses  https://www.ing.nl/api/locator/atms/ as its data source to fetch super set of ATM locations.
•	Application provides a page which can be used by the user to access API.
•	Application secures the page access using Spring-Security.

###Implementation Details: 
                   
•	Technology Stack:  Java 8, Spring MVC, Spring Boot, Spring Security, Junit, Mockito, Rest Template ,JQuery,  BootStrap.
•	Tools: Maven 3.3.9, Eclipse Mars 4.5.1, Tomcat 8, Eclemma, GIT.
•	Platform: Windows, Browser: Chrome.


 Method  |  URL | Action 	
 --- | --- | ---  
   GET |	/atmlocator/locate |	Returns a Atm location data for specified city.	

 

Usage:
•	Unzip the contents of BackBaseAtmLocator.zip in directory of your choice. 
•	Copy atmlocator.war to tomcat webapps directory.
•	Test Service end-point: 
$curl -u admin:admin123 --request GET 'http://localhost:8080/atmlocator/locate?city=Deventer'


•	Go to browser and type:  http://localhost:8080/atmlocator


•	Provide username/password: admin/admin123  (see configuration.)


Build from Source:  You can also run the service by building it from source  
	Get source from GIT : 

$ git clone https://github.com/bhupendrasingh26/atmlocator.git

	Build the project using maven :

$ mvn clean install 

	Rename the war: atmlocator-1.0.0.war to atmlocator.war

	Deploy at Tomcat


Configuration:
Application has following configuration options at application.properties(src/main/resources)
##### Service configuration settings #####
##########################################

# The address to get master atm location data:
url=https://www.ing.nl/api/locator/atms/


#####    Security settings           #####
##########################################

# Default username to access the page.
defaultUser=admin

# Default password to access the page.
defaultPassword=admin123

# Permissible roles for the user.
role=USER

# Context path for secured resources.
contextURI="/atmlocator"



   
### Screen Shots 
 


 

