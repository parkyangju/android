package com.yj.myapplication3;

import androidx.appcompat.app.AppCompatActivity;



import android.content.Intent;
import android.os.Bundle;



import android.view.View;
import android.widget.Button;

import android.widget.ListView;

public class MainActivity extends AppCompatActivity{

    ListView lvProgram;
    Button btn_1;

    String[] programName = {"광주 동구","광주 북구","광주 동구"};
    String[] programDescription = {"광주 동구","광주 동구","광주 동구"};
    int[] programImages = {R.drawable.pp,R.drawable.pp,R.drawable.pp,R.drawable.pp
            ,R.drawable.pp};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        lvProgram = findViewById(R.id.lv);
        btn_1 = findViewById(R.id.btn_1);

        ProgramAdapter adapter = new ProgramAdapter(this, programName, programImages, programDescription);
        lvProgram.setAdapter(adapter);
    }

        public void doSomething(View view){
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);



        }
    }

