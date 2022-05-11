package com.cookandroid.auth_app;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CsvReader implements Serializable {
    List<ListData> objects = new ArrayList<ListData>();
    public void reader(Context context, String whatfiless) {
        String whatfiles = whatfiless;
        try {
            //
            AssetManager assetManager = context.getResources().getAssets();
            InputStream inputStream = assetManager.open(whatfiles);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferReader.readLine()) != null) {

                //
                ListData data = new ListData();
                String[] RowData = line.split(",");

                //
                data.setbarcode(RowData[0]);
                data.setname(RowData[1]);
                data.setprice(RowData[2]);
                data.setEA(RowData[3]);
                data.setamount(RowData[4]);

                objects.add(data);
            }
            bufferReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
