package com.yugen.telegrambotV2.Configuration;

import com.yugen.telegrambotV2.Database.Requests.DBRequests;
import com.yugen.telegrambotV2.Telegram.TelegramBot;
import com.yugen.telegrambotV2.Youtube.YoutubeAPI;
import com.yugen.telegrambotV2.YugenBot.YugenBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;


@Configuration
@PropertySource("application.properties")
@ComponentScan("com.yugen.telegrambotV2")
public class ApplicationConfig {

    @Autowired
    private Environment environment;

    private YugenBot yugenBot;

    @Bean("telegramBot")
    @Scope(scopeName = "singleton")
    public TelegramBot getTelegramAPI() {
        String botUsername = environment.getProperty("telegram.botUsername");
        String botToken = environment.getProperty("telegram.botToken");
        return new TelegramBot(botUsername, botToken);
    }

    @Bean("databaseConnection")
    public DBRequests getDBConnection() {
        String ip = environment.getProperty("database.ip");
        String port = environment.getProperty("database.port");
        return new DBRequests(ip, port);
    }

    @Bean("yugenBot")
    public YugenBot getYugenBot() {

        if (yugenBot == null) {
            yugenBot = new YugenBot();
        }
        return yugenBot;
    }

    @Bean("youtubeAPI")
    public YoutubeAPI getYoutubeAPI() {
        String token = environment.getProperty("youtube.apiToken");
        return new YoutubeAPI(token);
    }
}

