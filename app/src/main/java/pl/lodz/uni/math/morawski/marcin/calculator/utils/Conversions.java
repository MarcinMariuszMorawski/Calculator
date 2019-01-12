package pl.lodz.uni.math.morawski.marcin.calculator.utils;

public final class Conversions {
    static public String convertDoubleNumberToString(double number){

        number = Math.floor(number * 100) / 100;
        String numberInString;
        numberInString = Double.toString(number);
        return numberInString;
    }
}
