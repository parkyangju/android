package com.yj.ex0323;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button btn,btn_back;
    EditText edt_input, edt_input1;
    int requestCode = 1;
    ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);
        btn_back = findViewById(R.id.btn_back);

        edt_input = findViewById(R.id.edt_input);
        edt_input1 = findViewById(R.id.edt_input1);

        layout = findViewById(R.id.layout);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edt_input.getText().toString();
                String pw = edt_input1.getText().toString();

                if(id.equals("hihi") && pw.equals("1234")){
                    Intent intent = new Intent(MainActivity.this,SubActivity.class);
                    intent.putExtra("id", id);
                    startActivity(intent);
                }

            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ListActivity.class);
                //갔다가 다시 오는것 보내주는 것만 하는게 아니다.
                startActivityForResult(intent,requestCode);

            }
        });
    }



    //오버라이딩 하고싶은곳 alt+insert 바로 검색

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //1.requestCode -> 요청코드값(어떤 버튼을 눌러서 갔다 온건지 구분하기 위해)
        //2.resultCode -> 결과 코드값(결과가 잘 넘어왔는지)
        //3. data-> 넘어온 결과값(생략가능, null일수도 있음)

        if(requestCode == this.requestCode){ //color 버튼을 클릭해서 다녀온건지
            if(resultCode == RESULT_OK){ //결과가 적용됐는지

                //intent에서 값 꺼내기
                String color = data.getStringExtra("color");

                //배경색 바꾸기
                layout.setBackgroundColor(Color.parseColor(color));
            }
        }




    }
}