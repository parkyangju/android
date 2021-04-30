package com.yj.ex0406;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Fragment_2 extends Fragment {
    // Thread 만들기
    // 1) Thread 클래스(설계도)를 정의한다. 객체를 위한 (Thread 상속)
    // 2) run 메소드(Thread 시작시켰을때 호출되는 메소드)를 오버라이딩 한다
    // 3)  settext(UI 업데이트)하기 위해 Handler생성
    //4) message 전송(sendMessage)하고 handler에서 값 전달받음
    //%) handler에서 settext 처리

    TextView tv1,tv2;
    Button btn1,btn2;
    cntThread thread=null;


   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View view = inflater.inflate(R.layout.fragment_2, container, false);

       tv1 = view.findViewById(R.id.tv1);
       tv2 = view.findViewById(R.id.tv2);
       btn1 = view.findViewById(R.id.btn1);
       btn2 = view.findViewById(R.id.btn2);

       btn1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               //Thread  객체를 생성한다.
               //1) Thread를 상솔받아 만들어진 클래스의 객체를 생성

               if(btn1.getText().toString().equals("START!")){
                   thread = new cntThread(tv1); //생성되는 thread가 필요한 textview를 보내주어야 함
                   thread.start();
                   btn1.setText("STOP!"); //start 버튼을 누르면 버튼에 적힌 글자가 stop으로 바뀌게 해봅시다
               }else{
                   thread.interrupt();
                   btn1.setText("START!");
                   //cpu 뺏기
                   // tread.interrupt를 하면 tread 내부에서는 interrupt Exception 이 발생함
                   // 멈추게 하려면 interrupt(); 그리고 return 작성-메서드 종료
               }
           }
       });

       btn2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               //Thread  객체를 생성한다.
               //1) Thread를 상솔받아 만들어진 클래스의 객체를 생성

               if(btn2.getText().toString().equals("START!")){
                   thread = new cntThread(tv2);
                   thread.start();
                   btn2.setText("STOP!"); //start 버튼을 누르면 버튼에 적힌 글자가 stop으로 바뀌게 해봅시다
               }else{
                   thread.interrupt();
                   btn2.setText("START!");
                   //cpu 뺏기
                   // tread.interrupt를 하면 tread 내부에서는 interrupt Exception 이 발생함
                   // 멈추게 하려면 interrupt(); 그리고 return 작성-메서드 종료
               }
           }
       });
       return view;

    }
    //Handler 만들기
    Handler handler = new Handler(){
      //메세지 수신 시 호출되는 메소드 생성

        @Override
        public void handleMessage(@NonNull Message msg) {
    //msg 에서 arg1에 저장된 값을 setText
            ((TextView)(msg.obj)).setText(msg.arg1+"");
        }

    };

    class cntThread extends Thread{
        private TextView textView;

        public cntThread(TextView textView){ //생성자에 매개변수 추가해주자 (매개변수로 Textview를 전달받는 생성자)
            this.textView = textView;
        }

        @Override
        public void run() { //Thread 시작했을때 호출되는 메소드
            //Thread가 할일을 적어주면 됨
            //1~10까지 1초씩 세는 코드

            for(int i=1; i<=10; i++){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    return;
                    //Thread 가 cpu를 사용하다가 양보함 => yield
                    //cpu를 뺏어옴 세치기함 ->Interrupt
                    //쉬는동안 (sleep) 다른 Thread가 interrupt걸었을때를 대비
                }

                //i=> TextView에 setText만 하면 됩니다~

                //★★★ (Sub Thread) =>내가만든 Thread)에서는 setText못함
                //Handler에 메세지를 전송해서 setText작업을 처리해야 함

                Message msg = new Message();
                //Message에는 변수가 3개있음
                //object, int arg1, int arg2
                //저 변수에는 각각 값을 저장할 수 있음

                //우리는 현재 i값을 handler에 보내서 TextView에 settext 할것
                msg.obj = textView;
                msg.arg1 = i;
                handler.sendMessage(msg);
            }

        }
    }
}