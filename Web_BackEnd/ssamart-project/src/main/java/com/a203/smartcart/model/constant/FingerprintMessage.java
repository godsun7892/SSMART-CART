package com.a203.smartcart.model.constant;

import lombok.Data;

@Data
public class FingerprintMessage {

    private String status;
    private String message;

    public FingerprintMessage(){
        this.status = "OK";
        this.message = "";
    }

    public  FingerprintMessage(String status, String message){
        this.status = status;
        this.message = message;
    }
}
