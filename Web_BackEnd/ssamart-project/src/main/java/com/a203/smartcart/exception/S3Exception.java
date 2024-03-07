package com.a203.smartcart.exception;

import com.a203.smartcart.model.constant.ErrorCode;

public class S3Exception extends RuntimeException {

    public S3Exception(ErrorCode errorCode) {
        super(errorCode.toString());
    }

}
