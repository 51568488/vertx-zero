package io.vertx.up.web;

import io.vertx.core.json.JsonObject;
import io.vertx.zero.test.UnitBase;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ZeroSerializerTc extends UnitBase {

    private <T> void put(final T input) {
        final JsonObject data = new JsonObject();
        data.put("data", ZeroSerializer.toSupport(input));
        getLogger().info("[TEST] Data = {0}, Type = {1}.",
                data.encode(), null == input ? "null" : input.getClass().getName());
    }

    @Test
    public void testInt() {
        put(2);
        put(new Integer("3"));
    }

    @Test
    public void testLong() {
        put(22);
        put(new Long("3"));
    }

    @Test
    public void testNull() {
        put(null);
    }

    @Test
    public void testDecimal() {
        put(2.33);
        put(3.33f);
        put(new BigDecimal("2.44"));
    }

    @Test
    public void testDate() {
        put(new Date());
        put(Calendar.getInstance());
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
    public void testUsers() {
        final User[] users = new User[2];
        users[0] = new User();
        put(users);
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
