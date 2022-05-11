package com.cookandroid.auth_app;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;


public class RecentReceiptView extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_receipt_view);

        setTitle("영수증 목록");

        AssetManager assetManager = getAssets();
        try {
            final String[] files = assetManager.list("");

            for(int i=0; i<files.length; i++){
                //Log.d("TestActivity", files[i]);
            }
            ListView list = (ListView) findViewById(R.id.filelist);

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, files);
            list.setAdapter(adapter);

            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //Toast.makeText(getApplicationContext(),files[position],Toast.LENGTH_SHORT).show();
                    //startActivity(new Intent(ProfileActivity.this, PreviousReceiptView.class));
                    Intent myintent = new Intent(RecentReceiptView.this, PreviousReceiptView.class);
                    myintent.putExtra("key",files[position]);
                    RecentReceiptView.this.startActivity(myintent);
                }
            });

        } catch (IOException e1) {
            e1.printStackTrace();
        }


    }
}
