package com.yael.springboot.api.gchat.gchat.application.interfaces.messages;




public enum EnumTypesMessages {
    UPDATE_MESSAGE ("update-message"),
    USER_JOINED_SERVER ("user-joined"),
    USER_LEAVE_SERVER ("user-leave"),
    NEW_MESSAGE ("new-message");


    private String type;

    private EnumTypesMessages(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
