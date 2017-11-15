package io.vertx.zero.eon;

import java.nio.charset.Charset;

/**
 * Value constants
 */
public interface Values {
    /** **/
    int BUFFER_SIZE = 16;
    /** **/
    String ENCODING = "UTF-8";
    /** **/
    Charset CHARSET = Charset.forName(ENCODING);
    /** **/
    int UNSET = -1;
    /** **/
    int CODECS = UNSET;
    /** **/
    int ZERO = 0;
    /** **/
    int IDX = 0;
    /** **/
    int ONE = 1;
    /** **/
    String TRUE = "true";
    /** **/
    String FALSE = "false";
    /** **/
    String NEW_LINE = "\n";
}
