# Enable Basic Authorization

You can enable authorization in zero system as following steps:

## 1. Configuration

In your configuration `vertx.yml`, you must define lime extend node as following:

```yaml
zero:
  lime: secure
```

Then it means that you must create new file named `vertx-secure.yml` instead with following content:

```
secure:
  # Standard Type
  mongo:
    type: mongo
    config:
      collectionName: DB_USER
      saltStyle: NO_SALT
```



