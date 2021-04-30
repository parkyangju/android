package com.yj.iotproject;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LEDSensor extends StringRequest {
    //node.js에 요청하는 URl
    final static  String URL="http://218.157.38.25:3000/devices/led";
    private Map<String, String> parameters;//node.js에 정보를 넘기는데 map 형식으로 넘겨주기 때문

    public LEDSensor(String led, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null); //요청되는 부분
        //flag=dht11
        //flag=mq2
        parameters=new HashMap<String, String>();
        parameters.put("flag",led);
    }

    @Override
    protected Map<String, String> getParams(){
        return parameters;
    }
}
