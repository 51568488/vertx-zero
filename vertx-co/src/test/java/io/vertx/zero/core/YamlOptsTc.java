package io.vertx.zero.core;

import io.vertx.core.json.JsonObject;
import io.vertx.zero.core.node.Opts;
import org.junit.Test;
import org.vie.exception.ZeroException;
import org.vie.exception.run.LimeFileException;
import top.UnitBase;

public class YamlOptsTc extends UnitBase {

    @Test(expected = LimeFileException.class)
    public void testYaml() throws ZeroException {
        final Opts<JsonObject> opts = Opts.get();
        final JsonObject errors = opts.ingest("erro");
        System.out.println(errors);
    }
}
