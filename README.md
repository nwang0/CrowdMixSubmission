# CrowdMixSubmission

## Instructions
### Run in eclipse:
1. Make sure Java 1.7 execution environment is bond to a specific JRE
2. Run "SocialNetworkLite" in the "Run" dropdown menu

### Run in terminal:
1. Install maven and ensure internet connections to mvn repository
2. cd to the project directory "social_network_lite"
2. mvn -f pom.xml exec:exec

## Design Considerations
### Potential extensions:
* Add New interface to interact with the application (GUI/Network).
* Support concurrently posting/viewing.
* Add new types of commands.

### Potential modifications
* The format of output is fixed into the Posting class for simplicity. If different kind of formats are required, we can pass in a formatter intead.

## Principles followed in the design
* SOLID. (especially Open/close principle)
* Avoid getter and setters to maximise encapsulation
* Domain driven design

