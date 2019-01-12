package pl.lodz.uni.math.morawski.marcin.calculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import pl.lodz.uni.math.morawski.marcin.calculator.database.DatabaseManager;
import pl.lodz.uni.math.morawski.marcin.calculator.utils.Calculator;


public class CalculatorActivity extends AppCompatActivity {

    private TextView expressionTextView;
    private final Calculator calculator = new Calculator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        expressionTextView = findViewById(R.id.calculatorTextView);
    }

    public void numberOrOperatorButtonClick(View v) {
        Button button = (Button) v;
        String buttonText = button.getText().toString();

        char c = buttonText.charAt(0);
        calculator.append(c);

        expressionTextView.setText(calculator.expression());

    }

    public void sumButtonClick(View v) {
        if (calculator.isEmpty()) {
            return;
        }

        String expression = calculator.expression();
        String solvedExpression;

        try {
            solvedExpression = calculator.solveExpression();
        } catch (ArithmeticException e) {
            solvedExpression = "Divide by 0!";
        } catch (Exception e) {
            solvedExpression = "Invalid expression!";
        }

        StringBuilder equation = new StringBuilder();
        equation.append(expression).append("=").append(solvedExpression);

        calculator.clear();
        expressionTextView.setText(equation);
        DatabaseManager.insertEquation(getBaseContext(), equation.toString());
    }


    public void listButtonClick(View v) {
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }

    public void clearButtonClick(View v) {
        calculator.clear();
        expressionTextView.setText("");
    }

    public void exitButtonClick(View v) {
        System.exit(0);
    }
}
