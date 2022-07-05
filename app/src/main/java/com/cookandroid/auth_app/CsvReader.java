package com.cookandroid.auth_app;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class CsvReader implements Serializable {
    List<ListData> objects = new ArrayList<ListData>();


    public void reader(Context context, String whatfiless, String whatemaill) {
        String whatfiles = whatfiless;
        String whatemail = whatemaill;
        try {
            //InputStream inputStream = new FileInputStream("/sdcard/Download/"+whatemail+"/"+whatfiles);
            //InputStream inputStream = new FileInputStream("/data/data/com.cookandroid.auth_app/files/"+whatemail+"/"+whatfiles);
            InputStream inputStream = new FileInputStream("/sdcard/Android/data/com.cookandroid.auth_app/files/"+whatemail+"/"+whatfiles);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferReader.readLine()) != null) {
                ListData data = new ListData();
                String[] RowData = line.split(",");//
                data.setname(RowData[0]);
                data.setprice(RowData[1]);
                data.setEA(RowData[2]);
                data.setamount(RowData[3]);

                objects.add(data);
            }
            bufferReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


