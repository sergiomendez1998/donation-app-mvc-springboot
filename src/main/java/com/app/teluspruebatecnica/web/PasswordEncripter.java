package com.app.teluspruebatecnica.web;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncripter {
//    method static to encript the password
    public static String encode(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}
