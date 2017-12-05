package io.vertx.zero.marshal.reliable;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.quiz.ZeroBase;
import io.vertx.up.tool.io.IO;
import io.vertx.zero.exception.DataTypeWrongException;
import io.vertx.zero.exception.RequiredFieldException;
import io.vertx.zero.exception.ZeroException;
import org.junit.Test;

public class JsonInsurerTc extends ZeroBase {

    @Test(expected = RequiredFieldException.class)
    public void testRequiredObj() throws ZeroException {
        final JsonObject rule = IO.getYaml(getFile("rule-required.yml"));
        final JsonObject data = IO.getJObject(getFile("rule-required-data.json"));
        ensure(RequiredInsurer.class, data, rule);
    }

    @Test(expected = RequiredFieldException.class)
    public void testRequiredArray() throws ZeroException {
        final JsonObject rule = IO.getYaml(getFile("rule-required.yml"));
        final JsonArray data = IO.getJArray(getFile("rule-required-array.json"));
        ensure(RequiredInsurer.class, data, rule);
    }

    @Test(expected = DataTypeWrongException.class)
    public void testTypedObj() throws ZeroException {
        final JsonObject rule = IO.getYaml(getFile("rule-typed.yml"));
        final JsonObject data = IO.getJObject(getFile("rule-typed-jobject.json"));
        ensure(TypedInsurer.class, data, rule);
    }

    @Test(expected = DataTypeWrongException.class)
    public void testTypedNumber() throws ZeroException {
        final JsonObject rule = IO.getYaml(getFile("rule-typed.yml"));
        final JsonObject data = IO.getJObject(getFile("rule-typed-number.json"));
        ensure(TypedInsurer.class, data, rule);
    }

    @Test(expected = DataTypeWrongException.class)
    public void testTypedDecimal() throws ZeroException {
        final JsonObject rule = IO.getYaml(getFile("rule-typed.yml"));
        final JsonObject data = IO.getJObject(getFile("rule-typed-decimal.json"));
        ensure(TypedInsurer.class, data, rule);
    }

    @Test
    public void testTypedCorrect() throws ZeroException {
        final JsonObject rule = IO.getYaml(getFile("rule-typed.yml"));
        final JsonObject data = IO.getJObject(getFile("rule-typed-correct.json"));
        ensure(TypedInsurer.class, data, rule);
    }
}
