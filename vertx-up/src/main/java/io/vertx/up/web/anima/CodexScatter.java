package io.vertx.up.web.anima;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.up.log.Annal;
import io.vertx.up.tool.io.Folder;
import io.vertx.up.tool.io.IO;
import io.vertx.up.web.ZeroCodex;
import io.vertx.zero.eon.FileSuffix;
import io.vertx.zero.eon.Strings;
import io.vertx.zero.exception.EmptyStreamException;

import java.util.List;

public class CodexScatter implements Scatter<Vertx> {

    private static final Annal LOGGER = Annal.get(CodexScatter.class);

    @Override
    public void connect(final Vertx vertx) {
        // 1. Load rules
        final List<String> rules = Folder.listFiles("codex", FileSuffix.YML);
        LOGGER.info(Info.SCANED_RULE, rules.size());
        // 2. Load request from rules
        for (final String rule : rules) {
            try {
                final String filename = "codex/" + rule;
                final JsonObject ruleData = IO.getYaml(filename);
                if (null != ruleData && !ruleData.isEmpty()) {
                    // File the codex map about the rule definitions.
                    ZeroCodex.getCodex().put(rule.substring(0, rule.lastIndexOf(Strings.DOT)), ruleData);
                }
            } catch (final EmptyStreamException ex) {
                LOGGER.vertx(ex);
            }
        }
    }
}
