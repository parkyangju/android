package com.yj.ex0319;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {
    Button back;
    TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        tv_result = findViewById(R.id.tv_result);

        //받는쪽은 new 안써준다
        Intent intent = getIntent();
        String str = intent.getStringExtra("data");
        tv_result.setText(str);

        back = findViewById(R.id.btn5);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); //현재 Activity 종료 - task 에서 삭제 (계속 데이터들이 쌓이지 않고 메인하나만 두고 다삭제)
            }
        });





    }
}