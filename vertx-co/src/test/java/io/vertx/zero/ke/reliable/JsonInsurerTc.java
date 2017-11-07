package io.vertx.zero.ke.reliable;

import com.vie.hors.ZeroException;
import com.vie.hors.ensure.RequiredFieldException;
import com.vie.ke.reliable.item.RequiredInsurer;
import com.vie.util.io.IO;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.junit.Test;
import top.UnitBase;

public class JsonInsurerTc extends UnitBase {

    @Test(expected = RequiredFieldException.class)
    public void testInsureObj() throws ZeroException {
        final JsonObject rule = IO.getYaml(getFile("rule-required.yml"));
        final JsonObject data = IO.getJObject(getFile("rule-required-data.json"));
        ensure(RequiredInsurer.class, data, rule);
    }

    @Test(expected = RequiredFieldException.class)
    public void testInsureArray() throws ZeroException {
        final JsonObject rule = IO.getYaml(getFile("rule-required.yml"));
        final JsonArray data = IO.getJArray(getFile("rule-required-array.json"));
        ensure(RequiredInsurer.class, data, rule);
    }
}
