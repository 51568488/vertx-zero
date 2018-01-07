package zava.lang.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class FantamHandler implements InvocationHandler {

    public Object bind(final Class<?> interfaceCls) {
        return Proxy.newProxyInstance(
                interfaceCls.getClassLoader(),
                new Class[]{interfaceCls},
                this
        );
    }

    @Override
    public Object invoke(final Object proxy,
                         final Method method,
                         final Object[] args) throws Throwable {
        return method.invoke(proxy, args);
    }
}
