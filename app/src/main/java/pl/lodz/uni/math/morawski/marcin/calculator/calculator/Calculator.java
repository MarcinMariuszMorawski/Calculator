package pl.lodz.uni.math.morawski.marcin.calculator.calculator;

import net.objecthunter.exp4j.*;

import java.util.ArrayList;
import java.util.List;

import pl.lodz.uni.math.morawski.marcin.calculator.utils.Conversions;

public final class Calculator {
    private final List<String> listOfSubsequentMathComponent;

    public static final String OPERATORS = "+-*/";
    public static final String STRING = "0123456789";
    public static final String DOT = ".";

    public Calculator() {
        listOfSubsequentMathComponent = new ArrayList<>();
    }

    public void append(char numberOrOperator) {

        String loadedChar = new StringBuilder().append("").append(numberOrOperator).toString();

        int lastElementNumber = listOfSubsequentMathComponent.size() - 1;
        String lastElement = "";
        String lastElementFistChar = "";

        if (listOfSubsequentMathComponent.size() > 0) {
            lastElement = listOfSubsequentMathComponent.get(lastElementNumber);
            lastElementFistChar = lastElement.substring(0, 1);
        }

        if (OPERATORS.contains(loadedChar)) {
            appendOperator(loadedChar, lastElement, lastElementFistChar, lastElementNumber);
            return;
        }

        if (STRING.contains(loadedChar)) {
            appendNumber(loadedChar, lastElement, lastElementFistChar, lastElementNumber);
            return;
        }

        if (DOT.contains(loadedChar)) {
            appendDot(lastElement, lastElementFistChar, lastElementNumber);
        }
    }

    private void appendDot(String lastElement, String lastElementFistChar, int lastElementNumber) {
        if (listOfSubsequentMathComponent.size() == 0) { // when list is empty we push 0.
            listOfSubsequentMathComponent.add("0.");
            return;
        }

        if (OPERATORS.contains(lastElement)) { //  when last element is operator we push 0.
            listOfSubsequentMathComponent.add("0.");
            return;
        }

        if (STRING.contains(lastElementFistChar)) { // when last operator is number we check for dot inside
            if (!lastElement.contains(".")) // if don't contains we push dot
            {
                listOfSubsequentMathComponent.set(lastElementNumber, lastElement + DOT);
                return;
            }
        }
    }


    private void appendNumber(String number, String lastElement, String lastElementFistChar, int lastElementNumber) {
        if (listOfSubsequentMathComponent.size() == 0) { // when list is empty we push number
            listOfSubsequentMathComponent.add(number);
            return;
        }
        if (OPERATORS.contains(lastElementFistChar)) { // when last element is operator we push number
            listOfSubsequentMathComponent.add(number);
            return;
        }
        if (STRING.contains(lastElementFistChar)) { // when last element is number we just add this number to same
            listOfSubsequentMathComponent.set(lastElementNumber, lastElement + number);
            return;
        }
    }

    private void appendOperator(String operator, String lastElement, String lastElementFistChar, int lastElementNumber) {
        if (listOfSubsequentMathComponent.size() == 0) { // when list is empty
            if ("-".equals(operator)) { // and we want to push - but not the other operator
                listOfSubsequentMathComponent.add(operator);
            }
            return;
        }

        if (!OPERATORS.contains(lastElementFistChar)) {
            //  we push a operator when no operator is before
            listOfSubsequentMathComponent.add(operator);
            return;
        }

        if (OPERATORS.contains(lastElementFistChar)) {
            //we exchange operator when last element is operator
            if (listOfSubsequentMathComponent.size() > 1) { // only when its not the first operator -
                listOfSubsequentMathComponent.set(lastElementNumber, operator);
            }
            return;
        }
    }


    public String solveExpression() {
        String result = "";

        if (isEmpty()) {
            listOfSubsequentMathComponent.clear();
            return result;
        }

        if (OPERATORS.contains(lastListElement())) {
            listOfSubsequentMathComponent.remove(listOfSubsequentMathComponent.size() - 1);
        }

        Expression expressionSolver = new ExpressionBuilder(expression()).build();

        try{
            double solvedExpression = expressionSolver.evaluate();
            result = Conversions.convertDoubleNumberToString(solvedExpression);
            return  result;
        }catch (ArithmeticException e) {
            result = "Divide by 0!";
            return  result;
        } catch (Exception e) {
            result = "Invalid expression!";
            return  result;
        }

    }


    public String expression() {
        if (listOfSubsequentMathComponent.size() == 0)
            return "";

        StringBuilder expression = new StringBuilder();

        for (String mathComponent : listOfSubsequentMathComponent) {
            expression.append(mathComponent);
        }

        return expression.toString();
    }

    public void clear() {
        listOfSubsequentMathComponent.clear();
    }

    public boolean isEmpty() {
        return listOfSubsequentMathComponent.size() <= 1;
    }

    private String lastListElement() {
        return listOfSubsequentMathComponent.get(listOfSubsequentMathComponent.size() - 1);
    }
}