package com.question.solvingQ.exception;

import lombok.Getter;

@Getter
public class ApiException extends Exception {
    private ApiExceptionType exType;
    private String exMsg;

    public ApiException(){
        super("SERVER ERROR");
        this.exMsg = "SERVER ERROR";
        this.exType = ApiExceptionType.SERVER_ERROR;
    }

    public ApiException(ApiExceptionType exType, String exMsg){
        super(exMsg);
        this.exMsg = exMsg;
        this.exType = exType;
    }

    public ApiException(ApiExceptionType exType){
        super(exType.name());
        this.exType = exType;
    }
}
