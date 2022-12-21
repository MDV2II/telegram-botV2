package com.yugen.telegrambotV2.Telegram;

import com.google.gson.Gson;
import com.yugen.telegrambotV2.Configuration.ApplicationConfig;
import com.yugen.telegrambotV2.Database.Requests.DBRequests;
import com.yugen.telegrambotV2.Database.Entity.UpdatesEntity;
import com.yugen.telegrambotV2.YugenBot.YugenBot;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TelegramBot extends TelegramLongPollingBot {

    private final String botUsername;
    private final String botToken;

    public TelegramBot(String botUsername, String botToken) {
        this.botUsername = botUsername;
        this.botToken = botToken;
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        DBRequests connection = ctx.getBean("databaseConnection", DBRequests.class);
        YugenBot yugenBot = ctx.getBean("yugenBot", YugenBot.class);

        UpdatesEntity updatesEntity = new UpdatesEntity();

        String gson = new Gson().toJson(update.getMessage());

        updatesEntity.setUpdateId(update.getUpdateId());
        updatesEntity.setChatId(Math.toIntExact(update.getMessage().getChatId()));
        updatesEntity.setJsonObj(gson);

        connection.save(updatesEntity);

        if (yugenBot.getUpdatesEntity() == null) {
            yugenBot.setUpdatesEntity(updatesEntity);
            yugenBot.start();
        }

    }
}
