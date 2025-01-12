package com.fuyuzhen.towatalk_backend.common;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
@Data
public class ServerResponse {
    Integer code;
    String message;
    Object data;

    public static  ServerResponse getInstance() {
        return new ServerResponse();
    }

    public  ServerResponse code(Integer code){
        this.code = code;
        return (ServerResponse) this;
    }

    public  ServerResponse message(String message){
        this.message = message;
        return (ServerResponse) this;
    }

    public  ServerResponse data(Object data){
        this.data = data;
        return (ServerResponse) this;
    }

    public  ServerResponse responseEnum(ResponseEnum responseEnum){
        this.code = responseEnum.code;
        this.message = responseEnum.message;
        return (ServerResponse) this;
    }

    @Override
    public String toString() {
        JSONObject resultJson = new JSONObject();
        resultJson.put("code", this.code);
        resultJson.put("message", this.message);
        resultJson.put("data", this.data);
        return resultJson.toString();
    }

    public static void main(String[] args) {
        System.out.println(ServerResponse.getInstance().responseEnum(ResponseEnum.LOGIN_SUCCESS));
    }
}

