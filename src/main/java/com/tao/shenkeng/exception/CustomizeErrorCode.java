package com.tao.shenkeng.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {

    QUESTION_NOT_FOUND("您找的问题不存在，要不换个试试？");

    private  String message;

    CustomizeErrorCode(String message) {
        this.message=message;
    }

    @Override
    public String getMessage() {
       return message;
    }
}
