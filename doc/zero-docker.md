# Docker Environment

## 0. Port List

* neo4j:
	* 6074 ( http://localhost:6074, neo4j:neo4j ) 
	* 6073
	* 6075 ( bolt://localhost:6075, Database Links )
* consul
	* 6500 ( http://localhost:6500 )
	* 6300
* mongo
	* 6017
* mysql
	* 6036 ( root:zero )
* postgres
	* 6432 ( postgres:zero )
* rabbit-mq
	* 6369
	* 6571
	* 6572
	* 6671
	* 6672 ( http://localhost:6672, guest:guest )
	* 6272
* redis
	* 6379

## 1. Setup
### 1.1. neo4j

```
docker pull neo4j
shell/zero-neo4j.sh
```

### 1.2. consul

```
docker pull consul:1.0.1
shell/zero-consul.sh
```

### 1.3. mongo

```
docker pull mongo:3.5.13
shell/zero-mongo.sh
```

### 1.4. mysql

```
docker pull mysql:5.7
shell/zero-mysql.sh
```

### 1.5. postgres

```
docker pull postgres:latest
shell/zero-postgres.sh
```

### 1.6. rabbitmq

```
docker pull rabbitmq:management
shell/zero-rabbit-mq.sh
```

### 1.7. redis

```
docker pull redis:4.0.2
shell/zero-redis.sh
```