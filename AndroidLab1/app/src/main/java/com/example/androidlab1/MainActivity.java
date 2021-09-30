package com.example.androidlab1;

import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText textInput = findViewById(R.id.inputText);
        TextView textOutput = findViewById(R.id.outputTextView);
        Button okButton = findViewById(R.id.okButton);
        Button cancelButton = findViewById(R.id.cancelButton);

        textOutput.setMovementMethod(new ScrollingMovementMethod());

        RadioGroup textSize = findViewById(R.id.textSize);
        textSize.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                switch (id) {
                    case R.id.size20RadioButton:
                        textOutput.setTextSize(20);
                        break;
                    case R.id.size24RadioButton:
                        textOutput.setTextSize(24);
                        break;
                }
            }
        });

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(textInput.getText().toString().equals("") || textSize.getCheckedRadioButtonId() == -1){
                    Toast.makeText(getApplicationContext(), "Some data is empty!", Toast.LENGTH_SHORT).show();
                }
                else {
                    textOutput.setText(textInput.getText());
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textOutput.setText("");
            }
        });
    }
}