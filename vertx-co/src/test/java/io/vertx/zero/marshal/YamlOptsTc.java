package io.vertx.zero.marshal;

import io.vertx.core.json.JsonObject;
import io.vertx.zero.exception.LimeFileException;
import io.vertx.zero.exception.ZeroException;
import io.vertx.zero.marshal.options.Opts;
import io.vertx.zero.test.UnitBase;
import org.junit.Test;

public class YamlOptsTc extends UnitBase {

    @Test(expected = LimeFileException.class)
    public void testYaml() throws ZeroException {
        final Opts<JsonObject> opts = Opts.get();
        final JsonObject errors = opts.ingest("erro");
        System.out.println(errors);
    }
}
