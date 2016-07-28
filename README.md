# spring-boot-demo

you need to see [spring-boot](https://github.com/spring-projects/spring-boot) firstly.

#### build the project
```bash
mvn clean install -DskipTests
```

#### run the project as you develop
```bash
mvn spring-boot:run
```

#### if you want to deploy the project , you can do this: (must install jdk1.8 and config the environment of **JAVA_HOME**)
```bash
java -jar target/spring-boot-demo-1.0-SNAPSHOT.war
```

#### if you want to look the dependency tree of this project:
```bash
mvn dependency:tree
```

### deploy to tomcat with maven
see [use maven to deploy this project](http://blog.csdn.net/xiejx618/article/details/15022233)


#### first deploy
```bash
mvn tomcat7:deploy
```
#### redeploy
```bash
mvn tomcat7:redeploy
```