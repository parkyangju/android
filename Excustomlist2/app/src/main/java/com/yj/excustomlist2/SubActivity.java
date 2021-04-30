package com.yj.excustomlist2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {

    EditText edt_title,edt_address;
    Button btn_ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        edt_title = findViewById(R.id.edt_title);
        edt_address = findViewById(R.id.edt_address);

        btn_ok = findViewById(R.id.btn_ok);

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = edt_title.getText().toString();
                String address = edt_address.getText().toString();

                Intent intent = new Intent();
                intent.putExtra("title",title);
                intent.putExtra("address",address);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}