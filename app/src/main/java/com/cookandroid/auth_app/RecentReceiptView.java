package com.cookandroid.auth_app;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
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


        AssetManager assetManager = getAssets();
        //asset폴더 매니저 선언

        //=================================================================================
        //String[] files = assetManager.list("");
        //asset폴더 자체를 디렉토리 설정하여 파일명을 files 배열로 저장
        //=================================================================================

//            =================================================================================
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        String userID = user.getUid();
        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("tiger","1111");
                User userProfile = snapshot.getValue(User.class);
                if(userProfile != null){
                    String email = userProfile.email;
                    //File myspace = new File("/sdcard/Download/"+email);
                    //File myspace = new File("/data/data/com.cookandroid.auth_app/files/"+email);
                    File myspace = new File("/sdcard/Android/data/com.cookandroid.auth_app/files/"+email);
                    Log.d("daa", myspace.toString());
                    files = myspace.list();


                    Log.d("tiger",files.toString());
                    // download/gustjq2536@gmail.com에 있는 파일들 files 배열로 저장
                    Log.d("tiger","2222");
                    for(int i=0; i<files.length; i++){
                        //Log.d("TestActivity", files[i]);
                    }
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
                            //Toast.makeText(getApplicationContext(),files[position],Toast.LENGTH_SHORT).show();
                            //startActivity(new Intent(ProfileActivity.this, PreviousReceiptView.class));

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
//            =================================================================================
//        Log.d("tiger","2222");
//        for(int i=0; i<files.length; i++){
//            //Log.d("TestActivity", files[i]);
//        }
//        ListView list = (ListView) findViewById(R.id.filelist);
//        // csv보여주는 리스트뷰 선언
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, files);
//        list.setAdapter(adapter);
//
//
//        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                //Toast.makeText(getApplicationContext(),files[position],Toast.LENGTH_SHORT).show();
//                //startActivity(new Intent(ProfileActivity.this, PreviousReceiptView.class));
//
//                Intent myintent = new Intent(RecentReceiptView.this, PreviousReceiptView.class);
//                //RecentReceiptView액티비티에서 PreviousReceiptView 액티비티로 이동하는 인텐트 선언
//                myintent.putExtra("key",files[position]);
//                //previousPreviousReceiptView액티비티로 intent를 이용해 클릭한 영수증 의 파일명을 가져옴
//                RecentReceiptView.this.startActivity(myintent);
//                //previousPreviousReceiptView액티비티로 이동
//            }
//        });


    }
}
