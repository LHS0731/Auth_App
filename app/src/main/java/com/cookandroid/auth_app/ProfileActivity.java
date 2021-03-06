package com.cookandroid.auth_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

public class ProfileActivity extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    private Button logout, PWreset, useCart, RecentReceipt, Qrpay;

    StorageReference storagereference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        logout = (Button) findViewById(R.id.signOut);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
            }
        });
        PWreset = (Button) findViewById(R.id.resetPassword);
        PWreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, ForgotPassword.class));
            }
        });
        useCart = (Button) findViewById(R.id.useCart);
        useCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, UsingCart.class));
            }
        });
        RecentReceipt = (Button) findViewById(R.id.recentReceipt);
        RecentReceipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, RecentReceiptView.class));
            }
        });
        Qrpay = (Button) findViewById(R.id.Qrpay);
        Qrpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, QRpay_Activity.class));
            }
        });


        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        storagereference = FirebaseStorage.getInstance().getReference();
        userID = user.getUid();

        final TextView greetingtTextView = (TextView) findViewById(R.id.greeting);
        final TextView fullNameTextView = (TextView) findViewById(R.id.fullName);
        final TextView emailTextView = (TextView) findViewById(R.id.emailAddress);
        final TextView ageTextView = (TextView) findViewById(R.id.age);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if(userProfile != null){
                    String fullName = userProfile.fullName;
                    String email = userProfile.email;
                    String age = userProfile.age;

                    greetingtTextView.setText("????????????") ;
                    fullNameTextView.setText(fullName);
                    emailTextView.setText(email);
                    ageTextView.setText(age);

                    File myDir = new File("/sdcard/Android/data/com.cookandroid.auth_app/files/"+email);
                    // ?????? files ????????? ??????????????? ????????? ?????????????????? ??????, ???????????? ??????????????? ?????? ????????? ?????? ======

                    // ?????? ??????????????? ???????????? ??????????????? ???????????????.
                    if (!myDir.exists()) {
                        try{
                            myDir.mkdir(); //?????? ???????????????.
                        }
                        catch(Exception e){
                            e.getStackTrace();
                        }
                    }else {
                    }

                    String data = "check csv files for a user!";
                    // ??????????????? ???????????? ?????? ???????????? ?????? ?????? txt????????? ??????????????? ??????.
                    storagereference.child(email+"/file.txt").putBytes(data.getBytes()).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        // ???????????? ??? ??????????????? ???????????? txt????????? ???????????????.
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfileActivity.this, "Error!:", Toast.LENGTH_LONG).show();

            }
        });
//
    }
}