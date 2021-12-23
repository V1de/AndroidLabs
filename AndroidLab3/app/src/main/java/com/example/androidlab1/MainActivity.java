package com.example.androidlab1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity implements InputFragment.OnInputListener, OutputFragment.OutputFragmentListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    FragmentManager fm = getSupportFragmentManager();

    @Override
    public void onInputListener(String string, int size) {
        OutputFragment outputFragment = (OutputFragment) fm.findFragmentById(R.id.outputFragment_container_view);
        outputFragment.setResult(string, size);
    }


    @Override
    public void OutputFragmentListener(String string) {
        InputFragment inputFragment = (InputFragment) fm.findFragmentById(R.id.inputFragment_container_view);
        inputFragment.setTextToInput(string);
    }
}