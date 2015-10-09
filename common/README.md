#Build and Deploy Adoreboard API

This document outlines the steps required to build and deploy the Adoreboard API. Some of the instructions may assume your OS is Ubuntu, but we hope to make our code and tools OS agnostic.

##1. Build

To build, run

	mvn clean package
	package.sh

##2. Deploy

###1. Pre-requisites

Install Tomcat e.g. on Ubuntu:

	sudo apt-get install tomcat7

###2. Configuration

####1. Initialise with default configuration:

	sudo mkdir -p /opt/adoreboard/configuration
	sudo cp common/src/main/resources/configuration/config.properties /opt/adoreboard/configuration/

####2. Edit configuration file to suit your local setup:

	sudo nano /opt/adoreboard/configuration/config.properties

#####MySQL configuration:
set jdbc.url to use the IP address of the MySQL host e.g. jdbc:mysql://10.0.0.23:3306/adoreboard ([install locally if needs be](../backend/README.md))

#####MongoDB congfiguration:
set mongodb.host to the Mongo host e.g. localhost ([install locally if needs be on dev machine](http://docs.mongodb.org/manual/tutorial/install-mongodb-on-ubuntu/))

#####Swagger documentation properties:
set domain.url for Swagger service to connect to e.g. http://snapshot.adoreboard.com:8080


#####Swagger documentation properties:
set domain.url for Swagger service to connect to e.g. http://snapshot.adoreboard.com:8080

####3. Initialise api with additional configuration

        sudo cp api/src/main/resources/configuration/api.properties /opt/adoreboard/configuration/

####4. Edit configuration file to suit your local setup:

        sudo nano /opt/adoreboard/configuration/api.properties

#####Swagger configuration:
set documentation.services.basePath to the api module host e.g. ${domain.url}/api

###3. Setup environment variable to point to configuration.

This is necessary because the package.sh command to copy environment variables to /etc/profile.d/adoreboard.sh is insufficient because the service command consequently drops all environment variables

Hack your Tomcat catalina script

	sudo nano /usr/share/tomcat7/bin/catalina.sh

And add the following line (to set ADOREBOARD_HOME / memory):

	CATALINA_OPTS="$CATALINA_OPTS -DADOREBOARD_HOME=file:/opt/adoreboard"
	CATALINA_OPTS="$CATALINA_OPTS -Xms512m -Xmx4092m -XX:PermSize=256m -XX:MaxPermSize=1024m"

Also to remove the weird WARNING messages from start of "catalina.out" logs run the following: -

    cd /usr/share/tomcat7
    sudo ln -s /var/lib/tomcat7/common/ common
    sudo ln -s /var/lib/tomcat7/server/ server
    sudo ln -s /var/lib/tomcat7/shared/ shared 

###4. Installation

To install, run

    sudo gdebi <location of adoreboard-api deb file>

###5. Access API

The API should now be accessible (after login) at http://localhost:8080/*version*/, with demoable documentation at [http://localhost:8080/*version*/docs](http://localhost:8080/0.5/docs).

You may need to configure security credentials to connect to the API. These are specified in the users table of the database, but you can login by default with *adoreuser:Manager1*.
