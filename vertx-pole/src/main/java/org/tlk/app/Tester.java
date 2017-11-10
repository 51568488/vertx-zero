package org.tlk.app;

class User<T> {

}

class Response<T> {

}

public class Tester {

    private static Response<User<String>> get(final Class<?> user) {

        return null;
    }

    public static void main(final String[] args) {
        final User<String> user = new User();
        final Response<User<String>> response
                = get(user.getClass());
    }
}
