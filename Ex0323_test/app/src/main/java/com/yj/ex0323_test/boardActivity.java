package com.yj.ex0323_test;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class boardActivity extends AppCompatActivity {

    EditText edt_input1;
    Button btn_ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        edt_input1 = findViewById(R.id.edt_input1);
        btn_ok = findViewById(R.id.btn_ok);

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = edt_input1.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("msg",msg);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}