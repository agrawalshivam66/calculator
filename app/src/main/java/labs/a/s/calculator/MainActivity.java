package labs.a.s.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    TextView t, a;
    String Svalue = "0";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t = (TextView) findViewById(R.id.textView);
        a = (TextView) findViewById(R.id.ans);
    }

    public void one(View view) {
        if (Svalue == "0") {
            Svalue = "1";
            PrintText(Svalue);
        } else {
            Svalue = Svalue + "1";
            PrintText(Svalue);
        }
    }

    public void two(View view) {
        if (Svalue == "0") {
            Svalue = "2";
            PrintText(Svalue);
        } else {
            Svalue = Svalue + "2";
            PrintText(Svalue);
        }
    }

    public void three(View view) {
        if (Svalue == "0") {
            Svalue = "3";
            PrintText(Svalue);
        } else {
            Svalue = Svalue + "3";
            PrintText(Svalue);
        }
    }

    public void clear(View view) {
        Svalue = "0";
        PrintText(Svalue);
        setFinalvalue(Svalue);
    }

    public void four(View view) {
        if (Svalue == "0") {
            Svalue = "4";
            PrintText(Svalue);
        } else {
            Svalue = Svalue + "4";
            PrintText(Svalue);
        }
    }

    public void five(View view) {
        if (Svalue == "0") {
            Svalue = "5";
            PrintText(Svalue);
        } else {
            Svalue = Svalue + "5";
            PrintText(Svalue);
        }
    }

    public void six(View view) {
        if (Svalue == "0") {
            Svalue = "6";
            PrintText(Svalue);
        } else {
            Svalue = Svalue + "6";
            PrintText(Svalue);
        }
    }

    public void nine(View view) {
        if (Svalue == "0") {
            Svalue = "9";
            PrintText(Svalue);
        } else {
            Svalue = Svalue + "9";
            PrintText(Svalue);
        }
    }

    public void eight(View view) {
        if (Svalue == "0") {
            Svalue = "8";
            PrintText(Svalue);
        } else {
            Svalue = Svalue + "8";
            PrintText(Svalue);
        }
    }

    public void seven(View view) {
        if (Svalue == "0") {
            Svalue = "7";
            PrintText(Svalue);
        } else {
            Svalue = Svalue + "7";
            PrintText(Svalue);
        }
    }


    public void zero(View view) {
        if (Svalue == "0") {
            Svalue = "0";
            PrintText(Svalue);
        } else {
            Svalue = Svalue + "0";
            PrintText(Svalue);
        }
    }

    public void delete(View view) {
        if (Svalue != null && Svalue.length() > 1) {
            Svalue = Svalue.substring(0, Svalue.length() - 1);
            PrintText(Svalue);
        } else {
            Svalue = "0";
            PrintText(Svalue);
        }
    }

    public void plus(View view) {
        Svalue = Svalue + " + ";
        PrintText(Svalue);
    }

    public void sub(View view) {
        Svalue = Svalue + " - ";
        PrintText(Svalue);
    }

    public void mul(View view) {

        Svalue = Svalue + " * ";
        PrintText(Svalue);

    }

    public void div(View view) {

        Svalue = Svalue + " / ";
        PrintText(Svalue);

    }

    public void equal(View view) {
        setFinalvalue(Svalue);
    }

    public void PrintText(String value) {

        t.setText(value);
    }

    public void setFinalvalue(String value) {
        double num = evaluate(value);
        String s1 = Double.toString(num);
        a.setText(s1);
    }


    public double evaluate(String expression) {
        char[] tokens = expression.toCharArray();

        // Stack for numbers: 'values'
        Stack<Double> values = new Stack<Double>();

        // Stack for Operators: 'operation'
        Stack<Character> operation = new Stack<Character>();

        for (int i = 0; i < tokens.length; i++) {
            // Current token is a whitespace, skip it
            if (tokens[i] == ' ')
                continue;

            // Current token is a number, push it to stack for numbers
            if (tokens[i] >= '0' && tokens[i] <= '9') {
                StringBuffer sbuf = new StringBuffer();
                // There may be more than one digits in number
                while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9')
                    sbuf.append(tokens[i++]);
                values.push(Double.parseDouble(sbuf.toString()));
            }

            //Here we are calculating and pushing high precedence values first into values stack
            //low precedence values are pushed into operation stack
            // Current token is an operator.
            else if (tokens[i] == '+' || tokens[i] == '-' ||
                    tokens[i] == '*' || tokens[i] == '/') {
                // While top of 'operation' has same or greater precedence to current
                // token, which is an operator. Apply operator on top of 'operation'
                // to top two elements in values stack
                while (!operation.empty() && hasPrecedence(tokens[i], operation.peek())) {
                    //if lower or equal precedence operator(tokens[i] is entered then operator is poped and value is calculated and pushed into value stack
                    values.push(applyOp(operation.pop(), values.pop(), values.pop()));
                }
                //  //if higher precedence operator(tokens[i]) is entered then push it directly
                // Push current token to 'operation'.
                operation.push(tokens[i]);
            }
        }


        // Entire expression has been parsed at this point, apply remaining
        // operation to remaining values
        while (!operation.empty())
            values.push(applyOp(operation.pop(), values.pop(), values.pop()));

        // Top of 'values' contains result, return it
        return values.pop();
    }

    // Returns true if 'op2' has higher or same precedence as 'op1',
    // otherwise returns false.
    public boolean hasPrecedence(char op1, char op2) {
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
            return false;
        else
            return true;
    }

    // A utility method to apply an operator 'op' on operands 'a'
    // and 'b'. Return the result.
    public double applyOp(char op, double b, double a) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0)
                    throw new UnsupportedOperationException("Cannot divide by zero");
                return a / b;
        }
        return 0;
    }

}