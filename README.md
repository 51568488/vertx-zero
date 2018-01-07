# Vertx Zero Up Framework

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/cn.vertxup/vertx-zero/badge.svg?style=plastic)](https://maven-badges.herokuapp.com/maven-central/cn.vertxup/vertx-zero/)
[![Apache License 2](https://img.shields.io/badge/license-ASF2-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0.txt)
[![Build Status](https://travis-ci.org/silentbalanceyh/vertx-zero.svg?branch=master)](https://travis-ci.org/silentbalanceyh/vertx-zero) 
[![Gitter](https://badges.gitter.im/JoinChat.svg)](https://gitter.im/vertx-up/Lobby)
[![Maintainability](https://api.codeclimate.com/v1/badges/d2d08e521276a496a94e/maintainability)](https://codeclimate.com/github/silentbalanceyh/vertx-zero/maintainability)
[![Waffle.io - Columns and their card count](https://badge.waffle.io/silentbalanceyh/vertx-zero.svg?columns=all)](https://waffle.io/silentbalanceyh/vertx-zero)

This project is based on Vert.x, the idea came from Spring-Boot framework. It could help developers focus on business requirements instead of more details of Vert.x. The project contains two parts "Up" and "Zero". "Up" means running up, "Zero" means no configuration provided, you could run your project with default configuration only.

Micro Service architecture is a future focused method to design and build mature system and help more companies to implement project faster, based on this idea, Vertx Zero Up Framework came out. It's micro-service oriented framework and will be verified by real projects. Also it provide a tool set to help developers to do correct things. The last point is that because of Event Driven Model, it's high performance framework.

This framework contains four sub-projects

* `vertx-co`: Core Library of Zero Up Framework
* `vertx-tp`: Third part integration components in Zero Up Framework
* `vertx-up`: Zero Up Engine with nested vert.x web container
* `vertx-rx`: Zero Up Engine with nested rxjava web container instead of web container
* `vertx-zeus`: Zero Examples, it will be removed in real project implementation. 

## 1. Envrionment

If you want to use Zero framework, you can add following dependency into you `pom.xml` to use Zero:

*Before 0.4.5.1 ( Direct )*

```xml
        <dependency>
            <groupId>cn.vertxup</groupId>
            <artifactId>vertx-up</artifactId>
            <version>0.4.5</version>
        </dependency>
```

*From 0.4.5.1 ( Inherit )*

```xml
    <parent>
        <artifactId>vertx-import</artifactId>
        <groupId>cn.vertxup</groupId>
        <version>0.4.5.1</version>
    </parent>
```

## 2. Boot Up

In your project, you can provide main entry only as following to run Zero \( Annotated with `@Up` \) .

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

Once the Zero is up, you can see following logs in your console \( The default port is 6083 \):

```
[ ZERO ] ZeroHttpAgent Http Server has been started successfully. \
    Endpoint: http://0.0.0.0:6083/
```

## 3. Documentation

### 3.1. Zero Documents

1. [Official Document](/doc/0-offical-document.md)

### 3.2. Useful Related Documents

1. [Issue resolutions \(1\)](doc/issues/README.md)

### 3.3. Some Notes

1. [RxJs](doc/rx/README.md)

## 4. Logging in Zero

You also could use following function in your coding to get Logger component.

```java
// Zero Logger initialized, connect to vert.x logging system directly but uniform managed by zero.
import io.vertx.up.log.Annal;

// Then in your class
public final class Statute {

    private static final Annal LOGGER = Annal.get(Statute.class);
    ......
}
```



