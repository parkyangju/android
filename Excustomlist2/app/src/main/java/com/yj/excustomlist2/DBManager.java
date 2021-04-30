package com.yj.excustomlist2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBManager extends SQLiteOpenHelper {

    public DBManager(@Nullable Context context) {
        super(context, "Direct.db", null, 1);
        //1. context-> 화면(Activity)정보
        //2. name -> DataBase 파일이름
        //3. factory => JDBC의 ResultSet과 같은 일을 하는 객체
        //4. version -> DataBase의 버전(최초로 만든 버전은 1을 주면 된다)


        //자식클래스의 생성자에 매개변수가 많아요
        //적당히 지워버리자 @ context만 남기고 그이후 super에 직접 써주었다.

    }

    //부모클래스에 매개변수가 있는 생성자가 있다면
    //자식클래스에서 반드시 부모클래스의 생성자를 호출해주어야 합니다
    //super -> 자식클래스에서만 호출이 가능하다
    @Override
    public void onCreate(SQLiteDatabase db) {
        //데이터베이스 파일이 최초로 생성될때 호출

        //1.테이블 생성하는 소스코드 입력하기
        String sql = "create table direct(title text, address text)";
        //text타입의 컬럼 두개 생성

        //2. sql 문장 전송하기(매개변수로 주어진 SQLiteDatabase 객체 사용)
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //데이터 베이스 파일의 버전이 바뀌었을때-어플삭제안해도 테이블 지우고싶을때 사용자에게 업데이트 요구
        //버전이 바뀌면 table 지우고 다시만드는 소스코드 입력
        String sql = "drop table direct";
        db.execSQL(sql); //테이블 지우기
        onCreate(db); //테이블 다시 생성

    }

    public void insert(String title, String address) {
        //리펙토링 -> 에러를 해결해나가는 방식으로 개발

        //1. 쓸수있는 데이터베이스 객체 불러오기
        SQLiteDatabase db = this.getWritableDatabase();
        //2. 데이터를 insert -> key, value 형태로 insert
        ContentValues cv = new ContentValues();
        cv.put("title", title); //컬럼명과 데이터를 매개변수로 넣어준다.
        cv.put("address", address);

        db.insert("direct",null, cv);
    }

    public Cursor getAllData() {
        //1. Database객체 꺼내기
        SQLiteDatabase db = this.getReadableDatabase(); //읽을수있는 db
        //2. 쿼리문 날리기
        String sql = "select * from direct";

        Cursor cs = db.rawQuery(sql, null);

        return cs;
    }

    public void delete(String text) {
        SQLiteDatabase db = this.getWritableDatabase(); //수정해야 하니까
        db.delete("direct","title='"+text+"'",null);
    }
}
