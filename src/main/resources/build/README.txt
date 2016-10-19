Installation

This guide will assist you in installing and prepare the environment to running the Growller project. 
If you have any feedback on how to improve this guide, if you encounter problems, or if you want to help out, do not hesitate to contact Us < renanrfs@gmail.com / pedroivo@x1s.eti.br >.

Prerequisites

This script assumes you have Java JDK 1.5+ (set as JAVA_HOME), and Ant 1.7+ installed.
If you don't, use the following links to download and install them:
	Java: http://java.sun.com/javase/downloads/index.jsp
	Ant: http://ant.apache.org/bindownload.cgi

Growller setup

The easiest way to get started is to simply run the installation script to install and prepare Growller setup. Simply go into the build folder and run:

ant growller.setup

This will:
	Download JBoss AS
	Download Eclipse
	Download Database Driver
	Install Database Driver in JBoss AS library folder
	Install Growller Datasource file in JBoss AS deploy folder	
	

Once the "growller.setup" has finished, you can start playing with the Growller project by complile it, deploy and starting the JBoss AS!

Manually installing the different components

If you don't want to use the "growller.setup", you can also use the individual ant targets, as described below.

Getting JBoss AS

You need to download and install JBoss AS. If you don't have it installed yet, go into the build folder and run the installation script:
ant install.jboss

This will download and install the server in ${jboss.home} folder. If you don't want it to download the zip file, you can put the jboss-as-*.zip in the ${jboss.home} folder.
If you already have it installed somewhere else, in build.properties, change the ${jboss.home} property to the location of your JBoss AS installation.

Getting Database Driver

You need to download and install Database driver. If you don't have it installed yet, go into the build folder and run the installation script:
ant install.db.files

This will download and install the Database driver in ${db.home} folder. If you don't want it to download the jar file, you can put the ${db.driver.jar.name} in the ${db.driver} folder.
If you already have it installed somewhere else, in build.properties, change the ${db.driver} property to the location of your Database driver jar file.

Prepare Project Files

You need to prepare the Growller project files (database driver and datasource) to access and management the Database project Após instalar os componentes necessários para correr o projeto Growller, 
ant prepare.project.files