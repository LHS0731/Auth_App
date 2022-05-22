package com.cookandroid.auth_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class QRpay_Activity extends AppCompatActivity {

    private Button btn_qr_scan, btn_get_receipt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrpay);

        btn_qr_scan = (Button) findViewById(R.id.qr_scan);
        btn_qr_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(QRpay_Activity.this, QRscanActivity.class));
            }
        });


    }
}