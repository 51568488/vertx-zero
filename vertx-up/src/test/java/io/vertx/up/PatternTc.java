package io.vertx.up;

import io.vertx.up.tool.io.IO;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTc {

    public void testPattern() {
        final String input = IO.getJObject("test/pattern.json").getString("key");
        System.out.println(input);
        final Pattern pattern = Pattern.compile(input);
        final Matcher matcher = pattern.matcher("/coeus/talk/lang");
        System.out.println(matcher.matches());
    }
}
