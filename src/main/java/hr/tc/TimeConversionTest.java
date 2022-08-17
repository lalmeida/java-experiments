package hr.tc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeConversionTest {

    @Test
    void testBasicAmConversion() {
        assertEquals("06:00:00", Result.timeConversion("06:00:00am"));
    }

    @Test
    void testBasicAmConversion2() {
        assertEquals("11:10:03", Result.timeConversion("11:10:03am"));
    }

    @Test
    void testBasicPmConversion1() {
        assertEquals("17:10:03", Result.timeConversion("05:10:03pm"));
    }

    @Test
    void testBasicPmConversion2() {
        assertEquals("13:11:59", Result.timeConversion("01:11:59pm"));
    }

    @Test
    void testMidnightConversion() {
        assertEquals("00:11:59", Result.timeConversion("12:11:59am"));
    }

    @Test
    void testMiddayConversion() {
        assertEquals("12:11:59", Result.timeConversion("12:11:59pm"));
    }




}
