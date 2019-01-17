package pl.lodz.uni.math.morawski.marcin.calculator;

import org.junit.Test;


import pl.lodz.uni.math.morawski.marcin.calculator.calculator.Calculator;

import static org.junit.Assert.assertEquals;


public class CalculatorTest {



    @Test
    public void calculatorClassCorrectResults() {
        Calculator calculator = new Calculator();
        assertEquals(calculator.solveExpression(), "");

        calculator.append('2');
        calculator.append('*');
        calculator.append('2');
        assertEquals(calculator.solveExpression(), "4,00");

        calculator.clear();
        assertEquals(calculator.solveExpression(), "");

        calculator.clear();
        calculator.append('f');
        calculator.append('*');
        calculator.append('f');
        assertEquals(calculator.solveExpression(), "");

        calculator.clear();
        calculator.append('5');
        calculator.append('/');
        calculator.append('0');
        assertEquals(calculator.solveExpression(), "Divide by 0!");

        calculator.clear();
        calculator.append('5');
        calculator.append('/');
        calculator.append('5');
        calculator.append('s');
        assertEquals(calculator.solveExpression(), "1,00");

        calculator.clear();
        calculator.append('5');
        calculator.append('/');
        calculator.append('/');
        calculator.append('/');
        assertEquals(calculator.solveExpression(), "5,00");

        calculator.clear(); // 8*8*0.8
        calculator.append('8');
        calculator.append('*');
        calculator.append('8');
        calculator.append('*');
        calculator.append('0');
        calculator.append('.');
        calculator.append('8');
        assertEquals(calculator.solveExpression(), "51,20");


        calculator.clear(); // 0...4*1
        calculator.append('0');
        calculator.append('.');
        calculator.append('.');
        calculator.append('.');
        calculator.append('4');
        calculator.append('*');
        calculator.append('1');
        assertEquals(calculator.solveExpression(), "0,40");
    }
}