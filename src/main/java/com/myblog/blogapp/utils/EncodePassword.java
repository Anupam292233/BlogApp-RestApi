package com.myblog.blogapp.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class EncodePassword {
    public static void main(String[] args) {
        PasswordEncoder encodedPassword = new BCryptPasswordEncoder();
        System.out.println(encodedPassword.encode("password"));
    }
}
