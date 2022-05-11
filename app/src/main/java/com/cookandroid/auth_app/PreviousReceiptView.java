package com.cookandroid.auth_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class PreviousReceiptView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_receipt_view);

        Intent intent = getIntent();
        String whatfiles_view = intent.getStringExtra("key");

        CsvReader parser = new CsvReader();
        parser.reader(getApplicationContext(),whatfiles_view);

        ListViewAdapter listViewAdapter = new ListViewAdapter(this, 0, parser.objects);
        ListView listView = (ListView)findViewById(R.id.list);
        listView.setAdapter(listViewAdapter);
    }
}