package pl.lodz.uni.math.morawski.marcin.calculator.utils;

import java.text.DecimalFormat;

public final class Conversions {
    static public String convertDoubleNumberToString(double number){

        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String numberInString = decimalFormat.format(number);
        return numberInString;
    }
}
