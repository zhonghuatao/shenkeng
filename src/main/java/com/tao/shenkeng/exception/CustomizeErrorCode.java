package com.tao.shenkeng.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {

    QUESTION_NOT_FOUND(2001,"您找的问题不存在，要不换个试试？"),
    TARGET_PARAM_NOT_FOUND(2002,"未选择评论或者问题"),
    NOT_LOGIN(2003,"登陆异常"),
    SYS_ERROR(2004,"系统冒烟了"),
    TYPE_PARAM_WRONG(2005,"TYPE不存在"),
    COMMENT_NOT_FOUND(2006,"评论不存在"),
    comment_is_empty(2007,"评论不能为空");

    private String message;
    private Integer code;
    CustomizeErrorCode(Integer code,String message) {
        this.code=code;
        this.message=message;
    }

    @Override
    public String getMessage() {
       return message;
    }
    @Override
    public Integer getCode() {
        return code;
    }
}
