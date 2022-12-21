package com.yugen.telegrambotV2.Database.Entity;
public class UpdatesEntity {


    private int Id;


    private int updateId;


    private int chatId;


    private String jsonObj;

    public UpdatesEntity() {
    }

    public UpdatesEntity(int updateId, int chatId, String jsonObj) {
        this.updateId = updateId;
        this.chatId = chatId;
        this.jsonObj = jsonObj;
    }

    public long getUpdateId() {
        return updateId;
    }

    public void setUpdateId(int updateId) {
        this.updateId = updateId;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public String getJsonObj() {
        return jsonObj;
    }

    public void setJsonObj(String jsonObj) {
        this.jsonObj = jsonObj;
    }
}
