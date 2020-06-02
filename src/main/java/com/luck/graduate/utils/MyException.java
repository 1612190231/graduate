package com.luck.graduate.utils;

import com.luck.graduate.utils.result.ResultEnum;

public class MyException extends RuntimeException {
    private Integer code;
    private String message;
    public MyException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }


    public MyException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }


    public MyException( String message) {
        super(message);
        this.message = message;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
