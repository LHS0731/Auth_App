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
        //asset폴더 매니저 선언
        try {
            final String[] files = assetManager.list("");
            //asset폴더 자체를 디렉토리 설정하여 파일명을 files에 문자열로 저장

            for(int i=0; i<files.length; i++){
                //Log.d("TestActivity", files[i]);
            }
            ListView list = (ListView) findViewById(R.id.filelist);
            // csv보여주는 리스트뷰 선언

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, files);
            list.setAdapter(adapter);


            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //Toast.makeText(getApplicationContext(),files[position],Toast.LENGTH_SHORT).show();
                    //startActivity(new Intent(ProfileActivity.this, PreviousReceiptView.class));
                    Intent myintent = new Intent(RecentReceiptView.this, PreviousReceiptView.class);
                    //RecentReceiptView액티비티에서 PreviousReceiptView 액티비티로 이동하는 인텐트 선언
                    myintent.putExtra("key",files[position]);
                    //previousPreviousReceiptView액티비티로 intent를 이용해 클릭한 영수증 의 파일명을 가져옴
                    RecentReceiptView.this.startActivity(myintent);
                    //previousPreviousReceiptView액티비티로 이동
                }
            });

        } catch (IOException e1) {
            e1.printStackTrace();
        }


    }
}
