# spring-boot-demo

you need to see [spring-boot](https://github.com/spring-projects/spring-boot) firstly.:smile:


## build and run
#### build the project
```bash 
mvn clean install -DskipTests
```

#### Running as a packaged application
if you want to deploy the project , you can do this: (must install jdk1.8 and config the environment of **JAVA_HOME**)

* run
```bash 
java -jar biz-demo/target/biz-demo-1.0-SNAPSHOT.jar
```
* debugging.Then, you can use the remote debugging.
```bash 
java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8000,suspend=n -jar biz-demo/target/biz-demo-1.0-SNAPSHOT.jar
```

#### Or using the Maven plugin to running
you should execute first:
```bash 
cd biz-demo
```
then execute:
```bash 
mvn spring-boot:run
```


## deploy to tomcat with maven 
see [use maven to deploy this project](http://blog.csdn.net/xiejx618/article/details/15022233)

### 1. start tomcat server
```bash
%TOMCAT_HOME%\bin\start.bat
```

### 2.deploy
#### first deploy
```bash 
mvn tomcat7:deploy
```
#### redeploy
```bash 
mvn tomcat7:redeploy
```


## Tips
#### if you want to look the dependency tree of this project:
```bash 
mvn dependency:tree
```