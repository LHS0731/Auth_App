package com.cookandroid.auth_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class PreviousReceiptView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_receipt_view);

        Intent intent = getIntent();
        //recentreceiptview로 부터 받은 인텐트를 intetnt로 저장
        String whatfiles_view = intent.getStringExtra("key_file");
        //whatfiles_view 문자열에 인텐트에 저장된 문자열을 받아옴(파일명)
        String whatemail_view = intent.getStringExtra("key_email");
        //whatfiles_view 문자열에 인텐트에 저장된 문자열을 받아옴(이메일)

        CsvReader parser = new CsvReader();
        parser.reader(getApplicationContext(),whatfiles_view, whatemail_view);
        //CsvReader내 reader메서드 실행, 이때 파일명을 입력함.
        Log.d("test3", parser.objects.toString());
        ListViewAdapter listViewAdapter = new ListViewAdapter(this, 0, parser.objects);
        ListView listView = (ListView)findViewById(R.id.list);
        listView.setAdapter(listViewAdapter);
    }
}
