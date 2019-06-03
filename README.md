# Grep Engine 
This is the application, that can be used as:
 1. *grep* command in UNIX;
 2. Local file browser.
 
# Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

## Prerequisites
What things you need to install the software and how to install them

    jre8
    maven
    nodejs
    npm
    
## Installing
For installing the application, you have to *clone* this repository from GitHub or just download sources.

## Running the application
### Running The server
In project root run the following commands: 

    mvn package 
    cd target/
    java -jar text-search-engine-0.0.1-SNAPSHOT.jar
    
### Running the client of Grep application 
Go to the client project https://github.com/DaNizz97/grep-engine-client

In project root run the following commands: 
    
    npm install
    npm run serve -- --port 8090
    
After that go to http://localhost:8090

### Running the client of File Browser application 
Go to the client project https://github.com/DaNizz97/file-browser

In project root run the following commands: 
        
    npm install
    npm run serve -- --port 8091
    
After that go to http://localhost:8090
   
