package io.vertx.up.func;

/**
 * Actuator function interface, this interface could throw out
 * java.lang.Exception but return void without any parameters.
 */
@FunctionalInterface
public interface JvmActuator {
    
    void execute() throws Exception;
}
