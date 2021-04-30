package com.yj.ex0323_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText edt_id,edt_pw;
    Button btn_check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edt_id = findViewById(R.id.edt_id);
        edt_pw = findViewById(R.id.edt_pw);

        btn_check = findViewById(R.id.btn_logincheck);

        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edt_id.getText().toString();
                String pw = edt_pw.getText().toString();

                if(id.equals("hihi") && pw.equals("1234")){
                    Intent intent = new Intent(); //빈봉투
                    intent.putExtra("id",id);
                    setResult(RESULT_OK,intent);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),"일치하지않습니다!",Toast.LENGTH_SHORT).show();
                    edt_id.setText(" ");
                    edt_pw.setText(" ");
                }
            }
        });
    }
}