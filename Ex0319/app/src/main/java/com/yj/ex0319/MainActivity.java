package com.yj.ex0319;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity{
    //MainActivity에다가 View.OnclickListener 를 생성했다
    Button btn;
    Button dial;
    Button call;
    Button next;
    EditText edt_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //버튼에 클릭리스너 달기
        //btn.findViewById(R.id.btn); btn 안에서 이 뷰(btn) 을 찾아보겠다.
        btn = findViewById(R.id.btn); //new 객체생성해서 사용 - 이거는 btn 이것만 사용가능하다
        dial = findViewById(R.id.btn2);
        call = findViewById(R.id.btn3);
        next = findViewById(R.id.btn4);
        edt_input = findViewById(R.id.edt_input);
        dial.setOnClickListener(new View.OnClickListener() { //이안에는 Listener만 들어갈수있다.
            @Override
            public void onClick(View v) {
                //1. intent 객체 생성하기 (액션, 데이터)
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.naver.com"));

                //2. intent 실행시키기
                startActivity(intent);

            }
        });

        dial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:01041815943"));
                startActivity(intent);
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:01041815943"));
                // 현재 권한이 부여되어있는지 검사
                if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED){

                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE},0);
                    return;
                }
                startActivity(intent);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Activity 실행시키기 보내는사람-> 받는사람
                Intent intent = new Intent(MainActivity.this,SubActivity.class);
                //데이터 여러개 담으려면 name만 다르게해서 여러번 만들어주면 됨
                intent.putExtra("data", edt_input.getText().toString());

                startActivity(intent);

            }
        });


    }


}