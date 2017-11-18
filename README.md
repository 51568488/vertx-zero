# vertx-zero

This project is based on Vert.x, the idea came from Spring-Boot framework. It could help developers focus on business requirements instead of more details of Vert.x. The project contains two parts "Up" and "Zero". "Up" means running up, "Zero" means no configuration provided, you could run your project with default configuration only. 

## 1. Boot Up

In your project, you can provide main entry only as following to run Zero ( Annotated with `@Up` ) .

```java
import io.vertx.up.VertxApplication;
import io.vertx.up.annotations.Up;

@Up
public class Driver {

    public static void main(final String[] args) {
        VertxApplication.run(Driver.class);
    }
}
```

Once the Zero is up, you can see following logs in your console ( The default port is 8083 ):

```
[ ZERO ] ZeroHttpAgent Http Server has been started successfully. \
	Endpoint: http://0.0.0.0:8083/
```

## 2. Documentation

1. [Getting Start](doc/zero-starter.md)
2. Parameters
	1. [@Path usage](doc/zero-path.md)
	2. [ ( JSR311 Extend ) @BodyParam usage](doc/zero-param.md)
	3. [Set POJO as parameters](doc/zero-pojo.md)
	4. [Parameter type supported](doc/zero-typed.md)
3. Request Flow
	1. [How to enable Event Bus](doc/zero-worker.md)
	2. [Mode supported describption](doc/zero-mode.md)
	3. [How to use Envelop ( Uniform Resource Model )](doc/zero-envelop.md)
4. Plugin
	1. [How to use MongoClient in Zero](doc/zero-mongo.md)
	2. [How to use MySqlClient in Zero](doc/zero-mysql.md)
5. DI
	1. [How to use Dependency Injection ( JSR330 )](doc/zero-di.md)
5. Exception ( Error )
	1. [How to define WebException](doc/zero-error.md)

## 3. Source Code

1. Pull code from github

	```
	git clone https://github.com/silentbalanceyh/vertx-zero.git
	```

2. Build the whole project to be sure all the testing case have been passed. ( Some testing will take 8080 port )

	```
	mvn clean package install
	```

## 4. Envrionment

Once you have built the project, you can add following dependency into you `pom.xml` to use Zero:

```xml
        <dependency>
            <groupId>io.vertx.up</groupId>
            <artifactId>vertx-up</artifactId>
            <version>0.4-SNAPSHOT</version>
        </dependency>
```

## 5. Code Structure

```
| - up
     | - atom				# Model definition
     | - eon				# Constant pool
     | - exception			# Exception definition
     | - func				# High order function
     | - plugin				# * Engine: Plugin Sub System
     | - rs				# * Engine: JSR311 implementation
     | - test				# Testing tools
     | - web				# * Engine: Web container core system
     | - annotations			# * Engine: JSR311 Annotation extends
| - zero
     | - atom				# Model definition
     | - eon				# Constant pool
     | - exception			# Exception definition
     | - func				# High order function
     | - log				# * Engine: Logger/Error Sub System
     | - marshal			# * Engine: Metadata/Config Sub System
     | - test				# Testing tools
     | - tool				# * Engine: Basic Infrastructure ( Utility Tool )
```

### Shared Package in each system.

* `atom`: Model definition ( Internal Domain Definition )
* `eon`: Constant pool 
* `exception`: Exception definition
* `func`: High order function
* `test`: Testing tools.

 
## 6. Dependency Library

*Lombok is standalone library, you can ignore this library in your project and write pojo with pure java. But we recommend you to use this library to simplify the POJO writting.*

* [Vert.x (3.5.0)](http://www.mvnrepository.com/artifact/io.vertx) ( core, web, config, config-yml, unit, zookeeper, hazelcast )
* [Jws Rs (2.1)](http://mvnrepository.com/artifact/javax.ws.rs/javax.ws.rs-api) 
* [Jersey Common (2.26)](http://mvnrepository.com/artifact/org.glassfish.jersey.core/jersey-common) ( For media type parsing )
* Logback
	* [logback-classic (1.2.3)](http://mvnrepository.com/artifact/ch.qos.logback/logback-classic)
	* [logback-core (1.2.3)](http://mvnrepository.com/artifact/ch.qos.logback/logback-core)
	* [jul-to-slf4j (1.7.25)](http://mvnrepository.com/artifact/org.slf4j/jul-to-slf4j)
	* [log4j-slf4j-impl (1.7.25)](https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-slf4j-impl)
* [Junit (4.12)](http://mvnrepository.com/artifact/junit/junit)
* [ReflectAsm (1.11.3)](http://www.mvnrepository.com/artifact/com.esotericsoftware/reflectasm/)
* [Jackson-Yaml (2.9.2)](http://www.mvnrepository.com/artifact/com.fasterxml.jackson.dataformat/jackson-dataformat-yaml)
* [Lombok (1.16.18)](http://mvnrepository.com/artifact/org.projectlombok/lombok)
