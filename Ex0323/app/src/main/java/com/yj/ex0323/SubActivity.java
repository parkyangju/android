package com.yj.ex0323;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {

    TextView tv_view;
    Button btn_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        tv_view = findViewById(R.id.tv_view);
        btn_main = findViewById(R.id.btn1);

        tv_view.setText(getIntent().getStringExtra("id"));

//        Intent intent = getIntent();
//        String str = intent.getStringExtra("id");
        tv_view.setText("님 로그인 성공!");

        btn_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }
}