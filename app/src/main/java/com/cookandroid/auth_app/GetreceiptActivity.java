package com.cookandroid.auth_app;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class GetreceiptActivity extends AppCompatActivity {

    Button down;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    StorageReference ref;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getreceipt);

        down = findViewById(R.id.btn_download);
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                download();
                downloadall();
            }
        });
    }

    public void download(){
        Intent intent = getIntent();
        String whatemail_view = intent.getStringExtra("key_email");

        storageReference=firebaseStorage.getInstance().getReference();
        ref=storageReference.child(whatemail_view+"/shopping.csv");
        Log.d("newlog",whatemail_view+"/shopping.csv");
        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

//                Intent intent = getIntent();
//                String whatemail_view = intent.getStringExtra("key_email");

                String url = uri.toString();
                downloadFiles(GetreceiptActivity.this, "shopping2", ".csv", "/"+whatemail_view, url);
                //downloadFiles(GetreceiptActivity.this, "abcd", ".csv", "/gustjq2536@gmail.com", url);
                //downloadFiles(GetreceiptActivity.this, "abcd", "csv", DIRECTORY_DOWNLOADS, url);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
    public void downloadall(){
        Intent intent = getIntent();
        String whatemail_view = intent.getStringExtra("key_email");

        storageReference listRef = storage.getReference().
    }
    public void downloadFiles(Context context, String fileName, String fileExtension, String destinationDirectory, String url){
        DownloadManager downloadManager = (DownloadManager) context.
                getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context, destinationDirectory, fileName + fileExtension);

        downloadManager.enqueue(request);
    }
}