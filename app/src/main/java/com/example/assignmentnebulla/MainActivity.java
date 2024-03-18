package com.example.assignmentnebulla;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.assignmentnebulla.databinding.ActivityMainBinding;
import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    MaterialButton buttonC, buttonAC,buttonOpenBracket,buttonCloseBracket;
    MaterialButton buttonAdd,buttonSubtract,buttonMultiply,buttonDivision,buttonEquals;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
    MaterialButton buttonDot;
    TextView solutionTv,resultTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignId(buttonC, R.id.button_c);
        assignId(buttonAC, R.id.buttonAC);
        assignId(buttonOpenBracket, R.id.buttonOpenBracket);
        assignId(buttonCloseBracket, R.id.buttonCloseBracket);
        assignId(buttonAdd, R.id.buttonAdd);
        assignId(buttonSubtract, R.id.buttonMinus);
        assignId(buttonMultiply, R.id.buttonMultiply);
        assignId(buttonDivision, R.id.buttonDivide);
        assignId(buttonEquals, R.id.buttonEquals);
        assignId(button0, R.id.button0);
        assignId(button1, R.id.button1);
        assignId(button2, R.id.button2);
        assignId(button3, R.id.button3);
        assignId(button4, R.id.button4);
        assignId(button5, R.id.button5);
        assignId(button6, R.id.button6);
        assignId(button7, R.id.button7);
        assignId(button8, R.id.button8);
        assignId(button9, R.id.button9);
        assignId(buttonDot, R.id.buttonPoint);
        solutionTv = findViewById(R.id.solutionTv);
        resultTv = findViewById(R.id.resultTv);
    }

    private void assignId(MaterialButton button, int id) {
        button = findViewById(id);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dateToCalculate = solutionTv.getText().toString();
        if (buttonText.equals("AC")) {
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }
        if (buttonText.equals("=")){
            solutionTv.setText(resultTv.getText());
            return;
        }
        if (buttonText.equals("C")){
            dateToCalculate = dateToCalculate.substring(0,dateToCalculate.length()-1);
            if (dateToCalculate.length()==0){
                solutionTv.setText("0");
            }
        }
        else {
            dateToCalculate = dateToCalculate+buttonText;
        }
        solutionTv.setText(dateToCalculate);
        String finalResult = getResult(dateToCalculate);
        if (!finalResult.equals("Err")){
            resultTv.setText(finalResult);
        }

    }
    String getResult(String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"JavaScript",1,null).toString();

            if (finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        } catch (Exception e){
            return "Err";
        }
    }

}