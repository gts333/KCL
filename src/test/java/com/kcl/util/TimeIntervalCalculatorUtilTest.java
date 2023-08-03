package com.kcl.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeIntervalCalculatorUtilTest {

    @Test
    void isTimeWithinAllowedHours() {
        //the result of this test is time dependent
        System.out.println(TimeIntervalCalculatorUtil.isTimeWithinAllowedHours("05_06_06", 48));
    }
}