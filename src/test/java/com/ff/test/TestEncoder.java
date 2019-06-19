package com.ff.test;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Author: FF
 * @Date: 2019/6/18 20:32
 * @Version 1.0
 */
public class TestEncoder {
    @Test
    public void encoder() {
        String password = "test";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(4);
        String enPassword = encoder.encode(password);
        System.out.println(enPassword);
    }
}
