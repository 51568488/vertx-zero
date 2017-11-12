package org.vie.util;

import org.junit.Test;
import top.UnitBase;

public class InstanceTc extends UnitBase {

    @Test
    public void testInteger() {
        final User user = new User();
        Instance.invoke(user, "invoke", 22);
    }

    @Test
    public void testString() {
        final User user = new User();
        Instance.invoke(user, "invoke", "String");
    }

    @Test
    public void testNoArg() {
        final User user = new User();
        Instance.invoke(user, "invoke");
    }

    @Test
    public void testWrapperInteger() {
        final Email email = new Email();
        Instance.invoke(email, "invoke", new Integer(22));
    }

    @Test
    public void testUnboxInteger() {
        final Email email = new Email();
        Instance.invoke(email, "invoke", 22);
    }
}

class Email {

    public void invoke(final Integer integer) {
        System.out.println("Integer: " + integer);
    }

    public void invoke(final int integer) {
        System.out.println("int: " + integer);
    }
}

class User {
    public void invoke() {
        System.out.println("Hello");
    }

    public void invoke(final String name) {
        System.out.println(name);
    }

    public void invoke(final int integer) {
        System.out.println(integer);
    }
}
