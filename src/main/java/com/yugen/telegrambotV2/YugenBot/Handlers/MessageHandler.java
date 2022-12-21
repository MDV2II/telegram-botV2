package com.yugen.telegrambotV2.YugenBot.Handlers;


import com.yugen.telegrambotV2.Telegram.TelegramExecutor;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class MessageHandler {
    private final Message message;

    public MessageHandler(Message message) {
        this.message = message;
    }

    public void controlMessage() {
        if (message.hasText()) {
            TextHandler textHandler = new TextHandler(message);
            textHandler.controlText();
        }

        new TelegramExecutor()
                .execute(new SendMessage(message.getChatId().toString(),
                "Incorrect request in Message"));
    }
}
