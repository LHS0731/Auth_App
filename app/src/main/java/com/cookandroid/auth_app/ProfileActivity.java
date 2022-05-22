package com.cookandroid.auth_app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;

public class ProfileActivity extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    private Button logout, PWreset, useCart, RecentReceipt, Qrpay;

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

                    greetingtTextView.setText("회원정보") ;
                    fullNameTextView.setText(fullName);
                    emailTextView.setText(email);
                    ageTextView.setText(age);

                    File dir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), email);
                    // 폴더가 만들어졌는지 체크, 로그인한 이메일로된 폴더 없을시 생성 ======
                    if(!dir.exists())
                        dir.mkdirs();
                    // 폴더가 제대로 만들어졌는지 체크 ======
                    Log.d("폴더생성", email);

                    File myDir = new File(getFilesDir(), email);
                    myDir.mkdir();
                    if(!myDir.exists())
                        myDir.mkdirs();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfileActivity.this, "Error!:", Toast.LENGTH_LONG).show();

            }
        });
//        folder_name = "/data/data/com.cookandroid.auth_app/files";
//        File newFolder = new File(folder_name);
//        try {
//            newFolder.mkdir();
//            Log.d("폴더생성", "폴더생성 성공");
//        } catch (Exception e) {
//            Log.d("폴더생성", "폴더생성이 이미 되어있거나 실패");
//        }


//        String foldername = emailTextView.getText().toString();
//        Log.d("sdadasd", foldername);
        // 폴더 만들기2 ======

    }
}