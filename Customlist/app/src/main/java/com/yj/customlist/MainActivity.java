package com.yj.customlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageView img;
    Button btn_call;
    TextView name,phone;
    ListView lv;

    ArrayList<ContactVO> contacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = findViewById(R.id.imageView);
        btn_call = findViewById(R.id.btn_call);
        name = findViewById(R.id.tv_name);
        phone = findViewById(R.id.tv_phone);

        lv = findViewById(R.id.listview);

        //arrayList에 연락처 정보 5개 추가

        contacts.add(new ContactVO(R.drawable.img1,"옴팡이","010-0000-1111"));
        contacts.add(new ContactVO(R.drawable.img2,"신난다","010-0000-1111"));
        contacts.add(new ContactVO(R.drawable.img3,"열심히","010-0000-1111"));
        contacts.add(new ContactVO(R.drawable.img4,"졸림다","010-0000-1111"));
        contacts.add(new ContactVO(R.drawable.img5,"운동쟁이","010-0000-1111"));

        ContactAdapter adapter = new ContactAdapter(getApplicationContext(),R.layout.contactlist, contacts);
        lv.setAdapter(adapter);

        //ArrayAdapter는 simplelist.xml 에 textView 단독으로 있을때만 사용가능!
        //그런데 지금 contactlist.xml에 custom을 해두었기 때문에 ArrayAdapter 사용 불가능!
        //해결방법 Adapter를 custom하자!


    }
}