package com.kmg.naver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class ContentActivity extends AppCompatActivity {

    private EditText edt_msnb_no,edt_msm_no, edt_msnb_subject, edt_msnb_content;
    private ImageButton btn_modify;
    private final int MODIFY = 1;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_content);

            //edt_msnb_no = findViewById(R.id.edt_msnb_no);
            //edt_msm_no = findViewById(R.id.msm_no);
            edt_msnb_subject = findViewById(R.id.msm_subject);
            edt_msnb_content = findViewById(R.id.msnb_content);


            btn_modify = findViewById(R.id.btn_add);




            btn_modify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(ContentActivity.this, boardActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"수정 완료",Toast.LENGTH_SHORT).show();

                }
            });


        }
}