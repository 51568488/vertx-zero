/**
 * Defined special package for internal exception definition.
 * 1. This kind of exceptions won't connect any Annal Logger
 * 2. This error messages use vertx logger directly.
 */
package org.vie.exception.run;

interface Info {
    /** **/
    String NIL_MSG =
            "[ZERO-RUN] Empty stream exception found when {0}, caused = {1}.";
    /** **/
    String JSON_MSG =
            "[ZERO-RUN] The system met decoding/encoding file {0} exception, caused = {1}.";
    /** **/
    String ECODE_MSG =
            "[ZERO-RUN] The code = {0} of error is missing in your file: " +
                    "vertx-error.yml, callee = {1}.";
    /** **/
    String ARG_MSG =
            "[ZERO-RUN] The method \"{0}\" of class \"{1}\" accept " +
                    "({3} {2}) arguments only, the length is conflict";
    String LIME_FILE = "[ZERO-RUN] Lime node configured file = \"{0}\"" +
            " is missing, please check the missed file";

}
