package io.vertx.up.tool;

import io.vertx.up.func.Fn;
import io.vertx.zero.eon.Values;

import java.security.MessageDigest;

public class Codec {

    /**
     * MD5 encript for input string.
     *
     * @param input
     * @return
     */
    public static String md5(final String input) {
        return Fn.getJvm(() -> {
            final MessageDigest digest = MessageDigest.getInstance("MD5");
            final byte[] source = input.getBytes(Values.CHARSET);
            digest.update(source);
            final byte[] middle = digest.digest();
            final char[] middleStr = new char[16 * 2];
            int position = 0;
            for (int idx = 0; idx < 16; idx++) {
                final byte byte0 = middle[idx];
                middleStr[position++] = Values.HEX_ARR[byte0 >>> 4 & 0xF];
                middleStr[position++] = Values.HEX_ARR[byte0 & 0xF];
            }
            return new String(middleStr);
        }, input);
    }
}
