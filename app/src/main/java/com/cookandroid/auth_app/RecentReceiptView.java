package com.cookandroid.auth_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
import java.text.Collator;
import java.util.Comparator;


public class RecentReceiptView extends AppCompatActivity {

    private DatabaseReference reference;
    private FirebaseUser user;
    private String[] files;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_receipt_view);

        setTitle("영수증 목록");

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        String userID = user.getUid();
        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                if(userProfile != null){
                    String email = userProfile.email;
                    File myspace = new File("/sdcard/Android/data/com.cookandroid.auth_app/files/"+email);
                    files = myspace.list();

                    ListView list = (ListView) findViewById(R.id.filelist);
                    // csv보여주는 리스트뷰 선언

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1, files);
                    final Collator c = Collator.getInstance();
                    adapter.sort(new Comparator<String>() {
                        @Override
                        public int compare(String lhs, String rhs) {
                            return c.compare(rhs, lhs);
                        }
                    });
                    list.setAdapter(adapter);


                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            Intent myintent = new Intent(RecentReceiptView.this, PreviousReceiptView.class);
                            //RecentReceiptView액티비티에서 PreviousReceiptView 액티비티로 이동하는 인텐트 선언
                            myintent.putExtra("key_file",files[position]);
                            myintent.putExtra("key_email",email);
                            //previousPreviousReceiptView액티비티로 intent를 이용해 클릭한 영수증 의 파일명을 가져옴
                            RecentReceiptView.this.startActivity(myintent);
                            //previousPreviousReceiptView액티비티로 이동
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }

        });

    }
}
