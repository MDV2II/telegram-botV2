package com.yugen.telegrambotV2.YugenBot;

import com.google.gson.Gson;
import com.yugen.telegrambotV2.Database.Entity.UpdatesEntity;
import com.yugen.telegrambotV2.YugenBot.Handlers.MessageHandler;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.Message;

public class YugenBot {

    @Getter
    @Setter
    private UpdatesEntity updatesEntity;

    public void start() {
        Message message = new Gson().fromJson(updatesEntity.getJsonObj(), Message.class);
        MessageHandler messageHandler = new MessageHandler(message);
        messageHandler.controlMessage();
    }

}
