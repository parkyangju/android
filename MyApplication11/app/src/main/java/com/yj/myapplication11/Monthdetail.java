package com.yj.myapplication11;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Monthdetail extends AppCompatActivity {

    TextView monthName, txtDays, txtHour;
    int days;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_detail);

        monthName = findViewById(R.id.monthName);
        txtDays = findViewById(R.id.txtDays);
        txtHour = findViewById(R.id.txtHour);

        Intent intent=getIntent();
        monthName.setText(intent.getStringExtra("select"));
        String Name=monthName.getText().toString();
        if(Name.equals("aaa")){
            days=30;
            txtDays.setText(txtDays.getText()+" "+days);
        }else if(Name.equals("abab")){
            days=28;
            txtDays.setText(txtDays.getText()+" "+days);
        }else{
            days=31;
            txtDays.setText(txtDays.getText()+" "+days);
        }
        txtHour.setText(txtHour.getText()+" "+days*24);

    }
}
