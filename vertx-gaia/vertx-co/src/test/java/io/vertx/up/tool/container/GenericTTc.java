package io.vertx.up.tool.container;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Type;

@SuppressWarnings("unchecked")
public class GenericTTc {

    @Test
    public void testGenericT() {
        final Class<User> clazz = (Class<User>) new TypeHod<User>() {
        }.getType();
        Assert.assertEquals(clazz, User.class);
        final Class<User> target = Proxy.print();
        System.out.println(target.getGenericSuperclass());
    }
}

class User {

}

class Proxy {

    public static <T> Class<T> print() {
        final Type type = (new TypeHod<T>() {
        }).getType();
        return (Class<T>) type;
    }
}
