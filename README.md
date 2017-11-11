# vertx-zero

This project is based on vertx, it's refer to Spring-Boot to implement light application framework for developers.

## 1. Pull code and build

1. Pull code from github

	```
	git clone https://github.com/silentbalanceyh/vertx-zero.git
	```

2. Build the whole project to be sure all the testing case have been passed. ( Some testing will take 8080 port )

	```
	mvn clean package install
	```


## 2. Dependency Library

* [Vert.x](http://www.mvnrepository.com/artifact/io.vertx) ( core, web, config, config-yml, unit, zookeeper, hazelcast )
* [Jws Rs](http://mvnrepository.com/artifact/javax.ws.rs/javax.ws.rs-api) 
* [Jersey Common](http://mvnrepository.com/artifact/org.glassfish.jersey.core/jersey-common) ( For media type parsing )
* Logback
	* [logback-classic](http://mvnrepository.com/artifact/ch.qos.logback/logback-classic)
	* [logback-core](http://mvnrepository.com/artifact/ch.qos.logback/logback-core)
	* [jul-to-slf4j](http://mvnrepository.com/artifact/org.slf4j/jul-to-slf4j)
	* [log4j-slf4j-impl](https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-slf4j-impl)
* [Junit](http://mvnrepository.com/artifact/junit/junit)
* [ReflectAsm](http://www.mvnrepository.com/artifact/com.esotericsoftware/reflectasm/)
* [Jackson-Yaml](http://www.mvnrepository.com/artifact/com.fasterxml.jackson.dataformat/jackson-dataformat-yaml)
* [Lombok](http://mvnrepository.com/artifact/org.projectlombok/lombok)

## 3. Usage

* [Getting Start](doc/vertx-zero.md)
