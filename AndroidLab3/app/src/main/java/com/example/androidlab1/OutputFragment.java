package com.example.androidlab1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import android.view.View;


public class OutputFragment extends Fragment {

    private TextView outputText;
    private Button cancelButton;
    private OutputFragmentListener listener;
    private DBHelper dbHelper;

    public OutputFragment() {
        super(R.layout.fragment_output);
    }

    public interface OutputFragmentListener {
        void OutputFragmentListener(String string);
    }

    public void setResult(String result, int size){
        outputText.setTextSize(size);
        outputText.setText(result);
        boolean success = dbHelper.addResult(result);
        if (success) {
            Toast.makeText(getContext(), "Result was added to DB!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (OutputFragmentListener) context;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        outputText = (TextView) view.findViewById(R.id.outputTextView);
        cancelButton = (Button) view.findViewById(R.id.cancelButton);
        dbHelper = new DBHelper(getContext());

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                outputText.setText("");
                listener.OutputFragmentListener("");
            }
        });

        Button dbButton = view.findViewById(R.id.dbButton);
        dbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), DBActivity.class);
                startActivity(intent);
            }
        });
    }
}