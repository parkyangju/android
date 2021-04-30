package com.yj.ex0318;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    // 에러 나는 곳에~~ 커서를 두시고~~ alt+enter 누릅니당!! 해보세용!!네

    //java 소스코드로 Button 에 Clicklister 다는법
    //1. MainActivity.class 파일에 onclickListner를 implement 해주기
    //2. OnClickListener 인터페이스의 onclick 메소드를 overriding해주기 (alt+enter)
    //3. 이벤트를 발생시키고 싶은 버튼에 clicklistener를 달아주기 (setonclicklistener

    Button btn_shake;
    ImageView img1, img2;
    TextView tv_score;
    Random random;
    int[] img_res = {R.drawable.dice1,R.drawable.dice2,R.drawable.dice3,
            R.drawable.dice4,R.drawable.dice5,R.drawable.dice6};
    int a =0; //누적을 해주어야 하기 때문에 전역변수로 지정
    int b =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_shake = findViewById(R.id.btn_shake);
        img1 = findViewById(R.id.iv_dice1);
        img2 = findViewById(R.id.iv_dice2);
        tv_score = findViewById(R.id.tv_score);

        btn_shake.setOnClickListener(this);
        random = new Random();
    }


    @Override
    public void onClick(View v) {
        //따로 랜던객체 안만들어줬을때
        //int num = new Random().nextInt(6);

        int num = random.nextInt(img_res.length);
        int num2 = random.nextInt(img_res.length);

        img1.setImageResource(img_res[num]); //배열에서 num번째의 이미지를 꺼내서 왼쪽이미지뷰 지정
        img2.setImageResource(img_res[num2]);

        if(num>num2){
            a++;
        }else if(num2>num){
            b++;
        }else{
            Toast.makeText(this,"무승부입니다.",Toast.LENGTH_SHORT).show();
        }
        tv_score.setText(a+" : "+b);

    }
}