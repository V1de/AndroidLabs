package com.example.androidlab1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class InputFragment extends Fragment {

    private EditText inputText;
    private Button okButton;
    private OnInputListener listener;
    private int size = 20;

    public void setTextToInput(String input){
        inputText.setText(input);
    }

    public InputFragment() {
        super(R.layout.fragment_input);
    }

    public interface OnInputListener {
        void onInputListener(String string, int size);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (OnInputListener) context;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        inputText = (EditText) view.findViewById(R.id.inputText);
        okButton = (Button) view.findViewById(R.id.okButton);

        RadioGroup textSize = view.findViewById(R.id.textSize);
        textSize.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                switch (id) {
                    case R.id.size20RadioButton:
                        size = 20;
                        break;
                    case R.id.size24RadioButton:
                        size = 24;
                        break;
                }
            }
        });

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputText.getText().toString().equals("")){
                    Toast.makeText(getActivity().getApplicationContext(), "Input data is empty!", Toast.LENGTH_SHORT).show();
                }
                else {
                    String text = inputText.getText().toString();
                    listener.onInputListener(text, size);
                }
            }
        });
    }

}