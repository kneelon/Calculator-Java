package com.example.calculatorloop;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private TextView resultTextView;
    private EditText editTextInput;
    private StringBuilder inputStringBuilder;
    private char operator = '+';
    private String result = "";
    private boolean isOperator = false;
    private boolean isDecimal = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = findViewById(R.id.textViewResult);
        editTextInput = findViewById(R.id.editTextInput);
        inputStringBuilder = new StringBuilder();

        setNumberButtonClickListeners();
        //setOperatorButtonClickListeners();
        setClearButtonClickListener();
        setEqualsButtonClickListener();
        setDecimalButtonClickListener();
        //buttonsClickListener();
        newButtonClickListener();
    }

    private void setNumberButtonClickListeners() {
        for (int i = 0; i <= 9; i++) {
            int buttonId = getResources().getIdentifier("button" + i, "id", getPackageName());
            Button button = findViewById(buttonId);
            int finalI = i;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    inputStringBuilder.append(finalI);
                    updateResultText();
                }
            });
        }
    }

    private void setOperatorButtonClickListeners() {
        char[] specialChars = {'+', '-', '*', '/'};
        String[] operators = {"Plus", "Minus", "Multiply", "Division"};

        for (int i = 0; i < specialChars.length; i++) {
            final char specialChar = specialChars[i];
            String buttonIdName = "button" + operators[i];
            int buttonId = getResources().getIdentifier(buttonIdName, "id", getPackageName());
            Button button = findViewById(buttonId);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isOperator = true;
                    isDecimal = false;
                    inputStringBuilder.append(" "+specialChar+" ");
                    operator = specialChar;
                    updateResultText();
                }
            });
        }
    }

    private void setClearButtonClickListener() {
        Button clearButton = findViewById(R.id.buttonC);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputStringBuilder.setLength(0);
                result = "0";
                isDecimal = false;
                isOperator = false;
                updateResultText();
                showResult();
            }
        });
    }

    private void setEqualsButtonClickListener() {
        Button equalsButton = findViewById(R.id.buttonEquals);
        equalsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String [] value = inputStringBuilder.toString().split(" ");
                switch (operator) {
                    case '+':
                        result = String.valueOf(Double.parseDouble(value[0]) + Double.parseDouble(value[2]));
                        break;
                    case '-':
                        result = String.valueOf(Double.parseDouble(value[0]) - Double.parseDouble(value[2]));
                        break;
                    case '*':
                        result = String.valueOf(Double.parseDouble(value[0]) * Double.parseDouble(value[2]));
                        break;
                    case '/':
                        result = String.valueOf(Double.parseDouble(value[0]) / Double.parseDouble(value[2]));
                        break;
                }
                showResult();
            }
        });
    }

    private void setDecimalButtonClickListener() {
        Button decimalButton = findViewById(R.id.buttonDot);
        decimalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isDecimal) {
                    inputStringBuilder.append(".");
                    updateResultText();
                }
            }
        });
    }

    private void updateResultText() {
        editTextInput.setText(inputStringBuilder.toString());
    }
    private void showResult() {
        resultTextView.setText(result);
    }

    private void newButtonClickListener() {
        final char[] characters = {'+', '-', '*', '/'};
        String[] ids = {"Plus", "Minus", "Multiply", "Division"};

        for (int i = 0; i < ids.length; i++) {
            String buttonIdName = "button" + ids[i];
            char special = characters[i];
            int buttonId = getResources().getIdentifier(buttonIdName, "id", getPackageName());
            Button button = findViewById(buttonId);
            int finalI = i;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isOperator = true;
                    isDecimal = false;
                    operator = characters[finalI];
                    //Toast.makeText(MainActivity.this, buttonId + " " +operator, Toast.LENGTH_SHORT).show();
                    inputStringBuilder.append(" "+special+" ");
                    updateResultText();
                }
            });
        }
    }
}
