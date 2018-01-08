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
java -jar biz-demo/target/biz-demo.war
```
* debugging.Then, you can use the remote debugging.
```bash 
java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8000,suspend=n -jar biz-demo/target/biz-demo.war
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

last, use browser to go http://localhost:8080/user/1 or http://127.0.0.1:8080/user/1

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

## Introduce
#### Swagger
see [https://springfox.github.io/springfox/](https://springfox.github.io/springfox/) to learn more.

 [Swagger使用总结](http://www.cnblogs.com/h9527/p/5506956.html)

 [RESTful API 利器 Swagger](http://www.razorer.com/2016/10/16/swagger-intro/)

 [Swagger：Rest API的描述语言](https://zhuanlan.zhihu.com/p/21353795)
#####swagger-ui
When you running the project with swagger , you can visit http://localhost:8080/swagger-ui.html#/.
Then input the swagger http api:http://localhost:8080/v2/api-docs.
