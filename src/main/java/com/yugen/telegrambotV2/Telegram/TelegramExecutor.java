package com.yugen.telegrambotV2.Telegram;

import com.yugen.telegrambotV2.Configuration.ApplicationConfig;
import com.yugen.telegrambotV2.Telegram.TelegramBot;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramExecutor {

    private final TelegramBot telegramBot;

    public TelegramExecutor() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        telegramBot = ctx.getBean("telegramBot", TelegramBot.class);
    }

    public void execute(Object object) {
        String className = object.getClass().getSimpleName();
        if (className.toLowerCase().contains("sendmessage")) {
            SendMessage response = (SendMessage) object;
            try {
                telegramBot.execute(response);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        } else if (className.toLowerCase().contains("sendphoto")) {
            SendPhoto response = (SendPhoto) object;
            try {
                telegramBot.execute(response);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
