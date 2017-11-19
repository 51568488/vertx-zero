# Function Programming

## 1. Function interface

Zero system extend function interface system based on java8.

* `java.util.function.Consumer<T>`：`void accept(T t)`；
* `java.util.function.BiConsumer<T, U>`：`void accept(T t, U u)`；
* `java.util.function.Predicate<T>`：`boolean test(T t)`;
* `java.util.function.Supplier<T>`：`T get()`；
* `java.util.function.Function<T, R>`：`R apply(T t)`；

Extension

* `io.vertx.up.func.JvmSupplier<T>`：`T get() throws Exception`；
* `io.vertx.up.func.JvmActuator`：`void execute() throws Exception`；
* `io.vertx.up.func.ZeroActuator`：`void execute() throws ZeroException`；
* `io.vertx.up.func.ZeroSupplier<T>`：`T get() throws ZeroException`；
* `io.vertx.up.func.Actuator`：`void execute()`；

## 2. Fn

In Zero system, there defined a supper static class for function abstract to simply the coding, this class is `io.vertx.up.func.Fn`, You also could use following function in your coding.

### 2.1. Fling

* **Fling** is similiar with throw out action, it means that the exception must be throw out in these actions.

```java

	/**
	 *  1. If the condition is true ( arg 0 ), Zero system will build the target Exception 
	 *  with Class<?> ( arg 2 ) and arguments ( arg 3 ).
	 *  2. Once exception build successfully, the Annal ( arg 1) will record 
	 **/
	Fn.flingZero(Boolean, Annal, Class<? extends ZeroException>, Object..)
		throws ZeroException;
	Fn.flingUp(Boolean, Annal, Class<? extends ZeroRunException>, Object..);



```