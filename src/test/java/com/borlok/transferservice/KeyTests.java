package com.borlok.transferservice;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Erofeevskiy Yuriy
 */


public class KeyTests {
    @Test
    public void test() {
        String test = new BCryptPasswordEncoder().encode("test");
        System.out.println(test);
    }
}
