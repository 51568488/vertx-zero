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

    String ENCODING_ISO_8859_1 = "ISO-8859-1";
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
    int TWO = 2;
    /** **/
    String TRUE = "true";
    /** **/
    String FALSE = "false";
    /** **/
    String NEW_LINE = "\n";
    /** **/
    char[] HEX_ARR = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
}
