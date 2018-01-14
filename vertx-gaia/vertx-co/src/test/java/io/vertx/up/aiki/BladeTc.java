package io.vertx.up.aiki;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ClassDeserializer;
import com.fasterxml.jackson.databind.ClassSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.vertx.core.json.JsonObject;
import io.vertx.quiz.ZeroBase;
import io.vertx.up.tool.Jackson;
import io.vertx.up.tool.io.IO;
import org.junit.Test;

public class BladeTc extends ZeroBase {

    @Test
    public void testBlade() {
        final JsonObject data = new JsonObject().put("email", "lang.yu@hpe.com");
        final Uson blade = Jackson.deserialize(data, Uson.class);
        System.out.println(blade);
    }

    @Test
    public void testClass() {
        final JsonObject data = new JsonObject().put("name", "io.vertx.up.aiki.TestObject");
        final TestObject result = Jackson.deserialize(data, TestObject.class);
        System.out.println(result.getClazz());
    }

    @Test
    public void testUson() {
        final JsonObject data = IO.getJObject(this.getFile("Uson.json"));
        final JsonObject result = Uson.create(data).convert("_id", "key").to();
        System.out.println(result.encodePrettily());
    }
}

class TestObject {

    @JsonSerialize(using = ClassSerializer.class)
    @JsonDeserialize(using = ClassDeserializer.class)
    @JsonProperty("name")
    private Class<?> clazz;

    public Class<?> getClazz() {
        return this.clazz;
    }

    public void setClazz(final Class<?> clazz) {
        this.clazz = clazz;
    }
}
