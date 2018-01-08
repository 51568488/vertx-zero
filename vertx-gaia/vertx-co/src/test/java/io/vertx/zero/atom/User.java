package io.vertx.zero.atom;

public class User {

    private String username;

    private String password;

    private Integer age;

    private String email;

    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(final Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + this.username + '\'' +
                ", password='" + this.password + '\'' +
                ", age=" + this.age +
                ", email='" + this.email + '\'' +
                '}';
    }
}
