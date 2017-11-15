package org.tlk.api;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private String name;
    private String email;
    private String qq;
    private String mobile;
    private int age;
    private Date birthday;
    private Date updated;
}
