package com.example.mycalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button AllClear , Clear, addsub ,equal;
    TextView input,output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            equal=findViewById(R.id.equal);
            addsub=findViewById(R.id.addsub);
            AllClear=findViewById(R.id.ac);
            input=findViewById(R.id.input);
            output=findViewById(R.id.output);
            Clear=findViewById(R.id.c);
            AllClear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    input.setText("");
                    output.setText("");
                }
            });

            addsub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   int num= Integer.parseInt(input.getText().toString());
                   num*=-1;
                    input.setText(String.valueOf(num));
                }
            });

            Clear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (input.getTouchables().isEmpty()){
                        String text  = input.getText().toString();
                        text =text.substring(0,text.length()-1);
                        input.setText(text);
                        output.setText("");
                    }

                }
            });

           equal.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                String data = input.getText().toString();
                   Context context = Context.enter();
                   context.setOptimizationLevel(-1);
                   Scriptable scriptable = context.initSafeStandardObjects();
                   String result = context.evaluateString(
                           scriptable,data,"Javascript",1,null).toString();
                   output.setText(result);
               }
           });

            return insets;
        });
    }

    public void getValue(View view) {
        Button btn = (Button) view;
        String value = btn.getText().toString();
        input.append(value);
    }




}