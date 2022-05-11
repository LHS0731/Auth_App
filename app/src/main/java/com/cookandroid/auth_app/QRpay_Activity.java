package com.cookandroid.auth_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

        btn_get_receipt = (Button) findViewById(R.id.get_receipt);
        btn_get_receipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(QRpay_Activity.this, GetreceiptActivity.class));
            }
        });


    }
}