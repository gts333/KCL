package com.kcl;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GeneralTest {

    @Test
    void test() {
        Integer i = Integer.valueOf(4);
        System.out.println(i.compareTo(Integer.valueOf(100)));
    }
}
