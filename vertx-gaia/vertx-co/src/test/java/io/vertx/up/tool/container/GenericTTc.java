package io.vertx.up.tool.container;

import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("unchecked")
public class GenericTTc {

    @Test
    public void testGenericT() {
        final Class<User> clazz = (Class<User>) new TypeHod<User>() {
        }.getType();
        System.out.println(clazz);
        Assert.assertEquals(clazz, User.class);
        final Class target = Proxy.print();
        System.out.println(target);
    }
}

class User {

}

class Proxy {

    public static <T> Class<T> print() {
        return (Class<T>) (new TypeHod<T>() {
        }).getType();
    }
}
