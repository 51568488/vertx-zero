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
    }
}

class User {

}
