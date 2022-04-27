package com.cookandroid.auth_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cookandroid.auth_app.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecentReceiptView extends AppCompatActivity {
    private final String FILE_NAME_1 = "receipt_sample_1.csv";
    private final String FILE_NAME_2 = "receipt_sample_2.csv";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_receipt_view);

        String filePath = getFilesDir().toString();

        JavaCsvHelper csvHelper = new JavaCsvHelper(filePath);

        /*** write ***/
        //테스트용 데이터 리스트1
        ArrayList<String[]> dataList1 = new ArrayList<>();

        //헤더로 사용할 데이터 추가
        dataList1.add(new String[] {"barcode", "name", "price", "EA", "amount"});

        //데이터 추가
        dataList1.add(new String[] {"ex1", "apple", "2000", "2", "4000"});
        dataList1.add(new String[] {"ex2", "banana", "3000", "1", "3000"});
        dataList1.add(new String[] {"ex3", "candy", "1000", "1", "1000"});
        dataList1.add(new String[] {"ex4", "ramyeon", "1000", "4", "4000"});
        dataList1.add(new String[] {"ex5", "milk", "3000", "3", "9000"});
        dataList1.add(new String[] {"21000", "", "", "", ""});

        csvHelper.writeAllData(FILE_NAME_1, dataList1);

        //테스트용 데이터 리스트2
        ArrayList<String[]> dataList2 = new ArrayList<>();

        //헤더로 사용할 데이터 추가
        dataList2.add(new String[] {"Name", "Age"});

        //데이터 추가
        dataList2.add(new String[] {"ex1", "apple", "2000", "2", "4000"});
        dataList2.add(new String[] {"ex2", "banana", "3000", "1", "3000"});
        dataList2.add(new String[] {"ex3", "candy", "1000", "1", "1000"});
        dataList2.add(new String[] {"ex4", "ramyeon", "1000", "4", "4000"});
        dataList2.add(new String[] {"ex5", "milk", "3000", "3", "9000"});
        dataList2.add(new String[] {"21000", "", "", "", ""});

        csvHelper.writeData(FILE_NAME_2, dataList2);



        /**
         * read
         * */
        //1번 파일 읽기
        List<String[]> dataList = csvHelper.readAllCsvData(FILE_NAME_1);
        for (String[] data : dataList) {
            Log.d("receipt_test", "data : " + Arrays.deepToString(data));
        }

        //2번 파일 읽기
        dataList = csvHelper.readCsvData(FILE_NAME_2);
        for (String[] data : dataList) {
            Log.d("receipt_test", "data : " + Arrays.deepToString(data));
        }

        final TextView greetingtTextView = (TextView) findViewById(R.id.view_Receipt);
       // greetingtTextView.setText(Arrays.deepToString(dataList[0]));
    }
}
