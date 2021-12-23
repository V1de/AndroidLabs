package com.example.androidlab1;

import android.database.DataSetObserver;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

public class DBActivity extends AppCompatActivity {

    private DBHelper dbHelper;
    private List<String> results;
    private ArrayAdapter resultsAdapter;
    private ViewStub listPlaceholder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbactivity);
        dbHelper = new DBHelper(getApplicationContext());
        listPlaceholder = findViewById(R.id.listStorageEmpty);

        ListView listView = findViewById(R.id.listStorage);
        results = dbHelper.getAllResults();
        resultsAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, results);
        resultsAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                listPlaceholder.setVisibility(results.isEmpty() ? View.VISIBLE : View.GONE);
            }
        });
        listView.setAdapter(resultsAdapter);
        resultsAdapter.notifyDataSetChanged();

        Button clearButton = findViewById(R.id.buttonClear);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.clearResults();
                results.clear();
                resultsAdapter.notifyDataSetChanged();
            }
        });

        Button backButton = findViewById(R.id.buttonBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}