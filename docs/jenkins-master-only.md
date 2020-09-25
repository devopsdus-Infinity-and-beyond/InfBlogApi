# Install Tools in Jenkins

## Maven

Just select install from apache

![maveninstall](2020-09-04-19-46-02.png)

## JDK

### Install the jdk

Get the newest version from [AdoptOpenJDK](https://github.com/AdoptOpenJDK/openjdk14-binaries/releases/download). Use the Linux amd64 build.

![jdkinstall](2020-09-04-19-43-53.png)

### Set Paths and Variables for JDK in the global config

![jdkpath](2020-09-04-19-41-51.png)

Warning: (Dont use jdk14 as a tool in the pipeline. This will overwrite the JAVA_HOME variable with an incorrect path)
