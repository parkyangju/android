package com.yj.ex0323_test;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> list = new ArrayList<String>(); //Listview에 띄울 데이터
    ArrayAdapter<String> adapter; //새로 계속 새로고침을 해주어야 해서 전역변수로 만들어준다

    Button login,write;
    TextView tv_view;
    ListView lv;

    final int loginCode = 1;
    final int Write = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.btn_login);
        write = findViewById(R.id.btn_write);

        tv_view = findViewById(R.id.tv_view);
        lv = findViewById(R.id.listview);

        adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.boardlist,list);
        lv.setAdapter(adapter);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivityForResult(intent, loginCode);
            }
        });

        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this,boardActivity.class),Write);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == loginCode) { //뭐때문에 다녀온거니? 로그인코드때문에
            if (resultCode == RESULT_OK) { //ok 받았다면
                //tv_view.setText(data.getStringExtra("id")+"님 환영합니다!"); 이렇게 써도됨
                String id = data.getStringExtra("id");
                tv_view.setText(id+"님 로그인 성공!! ^.^");
                login.setText("로그아웃");
                write.setEnabled(true);
            }
        }else if(requestCode == Write){
            if(resultCode == RESULT_OK){
                list.add(data.getStringExtra("msg"));
                adapter.notifyDataSetChanged();
            }
        }
    }

}