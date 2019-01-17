package pl.lodz.uni.math.morawski.marcin.calculator;

import org.junit.Test;

import pl.lodz.uni.math.morawski.marcin.calculator.utils.Conversions;

import static org.junit.Assert.assertEquals;


public class ConversionsTest {


    @Test
    public void convertDoubleNumberToStringCorrectResults() {
        assertEquals("2,00", Conversions.convertDoubleNumberToString(2.0));
        assertEquals("2,00", Conversions.convertDoubleNumberToString(2));
        assertEquals("-3,00", Conversions.convertDoubleNumberToString(-3));
        assertEquals("2,33", Conversions.convertDoubleNumberToString(2.3333333333));
        assertEquals("0,00", Conversions.convertDoubleNumberToString(0));
        assertEquals("0,00", Conversions.convertDoubleNumberToString(-0));
    }
}
