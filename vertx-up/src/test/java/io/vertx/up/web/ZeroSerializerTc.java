package io.vertx.up.web;

import io.vertx.core.json.JsonObject;
import io.vertx.quiz.ZeroBase;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class ZeroSerializerTc extends ZeroBase {

    private <T> void put(final T input) {
        final JsonObject data = new JsonObject();
        data.put("request", ZeroSerializer.toSupport(input));
        getLogger().info("[TEST] Data = {0}, Type = {1}.",
                data.encode(), null == input ? "null" : input.getClass().getName());
    }

    @Test
    public void testInt() {
        put(2);
    }

    @Test
    public void testInt1() {
        put(new Integer("3"));
    }

    @Test
    public void testLong() {
        put(new Long("3"));
    }

    @Test
    public void testLong2() {
        put(22L);
    }

    @Test
    public void testNull() {
        put(null);
    }

    @Test
    public void testDecimal() {
        put(3.33f);
        put(new BigDecimal("2.44"));
    }

    @Test
    public void testDecimal2() {
        put(2.33);
    }

    @Test
    public void testDecimal3() {
        put(3.33f);
    }

    @Test
    public void testDate() {
        put(new Date());
    }

    @Test
    public void testDate1() {
        put(Calendar.getInstance());
    }

    @Test
    public void testDate2() {
        put(LocalDate.now());
        put(LocalTime.now());
        put(LocalDateTime.now());
    }

    @Test
    public void testUser() {
        put(new User());
    }

    @Test
    public void testUserList() {
        final List<User> users = new ArrayList<>();
        users.add(new User());
        put(users);
    }

    @Test
    public void testBuffer() {
        final StringBuffer buffer = new StringBuffer("Buffer");
        put(buffer);
    }

    @Test
    public void testSet() {
        final Set<String> sets = new HashSet<>();
        sets.add("Hello");
        put(sets);
    }

    @Test
    public void testUsers() {
        final User[] users = new User[2];
        users[0] = new User();
        put(users);
    }

    @Test
    public void testStringArray() {
        final String[] str = new String[2];
        str[0] = "Hello";
        str[1] = "Lang";
        put(str);
    }

    @Test
    public void testBytes() {
        final byte[] bytes = new byte[16];
        put(bytes);
    }

    @Test
    public void testEnum() {
        put(KTest.STRING);
    }
}

enum KTest {
    STRING,
    VALUE
}

class User {
    String name = "Lang";
    String email = "silentbalanceyh@126.com";

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }
}
