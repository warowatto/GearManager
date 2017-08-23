package com.shinheung.gearmanager;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {

        String string = "24.50.07";

        System.out.println(string.split("\\.").length - 1);
    }
}