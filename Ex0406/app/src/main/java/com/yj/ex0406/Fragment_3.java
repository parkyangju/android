package com.yj.ex0406;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Fragment_1 extends Fragment {

    TextView tv_count;
    ImageView[] imgArray = new ImageView[9];
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_count = findViewById(R.id.tv_count);

        for(int i=0; i<imgArray.length; i++){
            //상수정의
            final int pos = i;

            int img_id = getResources().getIdentifier("img"+(i+1),"id", getPackageName());
            imgArray[i] = findViewById(img_id);

            //두더지의 상태 (on,off)
            imgArray[i].setTag("off"); //상태저장

            //두더지 Tread 실행
            Thread doThread = new doThread(i);
            doThread.start();

            //클릭 이벤트 적용 (익명 클래스)
            imgArray[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //두더지 상태에 따른 상태 카운트
                    if(imgArray[pos].getTag().toString().equals("on")){
                        Toast.makeText(MainActivity.this,"잡았다!",Toast.LENGTH_SHORT).show();
                        score++;
                    }else{
                        score--;
                    }
                    //String.valueOf 정수를 문자열로 변환
                    tv_count.setText(String.valueOf(score));
                }
            });

        }
    }//end oncreate

    Handler doHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {

            //두더지에 대한 상태, 이미지등을 변경
            imgArray[msg.arg1].setImageResource(msg.arg2);
            imgArray[msg.arg1].setTag(msg.obj);
        }
    };

    class doThread extends Thread{

        int pos = 0; //두더지 번호

        public  doThread(int pos){ //두더지 번호 초기화
            this.pos = pos;
        }

        @Override
        public void run() {

            while (true) {

                //각 다르게 초를 정해주자
                int offTime = new Random().nextInt(5000) + 500; //5000초 지정해주기
                int onTime = new Random().nextInt(3000) + 500;

                try {
                    Thread.sleep(offTime); //0~4.5초동안 머물기
                    Message msg = new Message();

                    //핸들러에게 전달할 값 : 상태값(on), on.png파일명, 몇번쨰 두더지인지
                    msg.arg1 = pos; //몇번쨰 두더지인지
                    msg.arg2 = R.drawable.on; //어떤사진으로 바꿀껀지
                    msg.obj = "on"; //상태값

                    doHandler.sendMessage(msg);//헨들러 값 넘겨줌

                    //두더지 숨기는 기능 구현하기
                    Thread.sleep(onTime);
                    //msg = new Message(); 로 초기화 해도됨

                    Message msg1 = new Message();

                    msg1.arg1 = pos;
                    msg1.arg2 = R.drawable.off;
                    msg1.obj = "off";

                    doHandler.sendMessage(msg1);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}