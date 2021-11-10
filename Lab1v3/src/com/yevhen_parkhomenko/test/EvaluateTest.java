package com.yevhen_parkhomenko.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static com.yevhen_parkhomenko.app.Evaluate.evaluate;
import static com.yevhen_parkhomenko.app.Evaluate.atPointParser;

class EvaluateTest {
    @Test
    public void unitTest1(){
        String test = " 2*(2+2)*2^2*2";
        System.out.println(test);
        test = String.valueOf(evaluate(test));
        String answer = "64.0";
        System.out.println("Real answer: " + test + " Expected answer: " + answer + "\n");
        Assertions.assertEquals(answer, test);
    }
    @Test
    public void unitTest2(){
        Object[][] formulas = new String[5][5];
        formulas[3][3] = "@C3";
        formulas[2][2] = "-5";
        HashSet<String> set = new HashSet<>();
        set.add("A1");
        String test = "mmax(mmin(mmax(0, 3), mmin(3, 5), mmin(2^2+2, 3^2-2)), @D4)";
        System.out.println(test);
        try {
            test = atPointParser(test, set, formulas, 5, 5);
            test = String.valueOf(evaluate(test));
            String answer = "3.0";
            System.out.println("Real answer: " + test + " Expected answer: " + answer + "\n");
            Assertions.assertEquals(answer, test);
        }
        catch (Exception ex){
            Assertions.assertEquals("1", "0");
        }
    }
    @Test
    public void unitTest3(){
        String test = "min(3,)";
        System.out.println(test);
        try {
            test = String.valueOf(evaluate(test));
        }
        catch (Exception ex){
            test = "ERROR";
        }
        String answer = "ERROR";
        System.out.println("Real answer: " + test + " Expected answer: " + answer + "\n");
        Assertions.assertEquals(answer, test);
    }
}

