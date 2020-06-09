package com.tao.shenkeng.enums;

public enum  CommentTypeEnum {
    QUESTION(1),
    COMMENT(2);
    private int type;

    public static boolean isExist(Integer type) {
        for (CommentTypeEnum commentTypeEnum:CommentTypeEnum.values()){
            if(commentTypeEnum.getType()==type){
                return true;
            }
        }
        return false;
    }

    public int getType() {
        return type;
    }

    CommentTypeEnum(int type) {
        this.type = type;
    }
}
