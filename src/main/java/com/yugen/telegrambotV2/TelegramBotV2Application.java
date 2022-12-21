package com.yugen.telegrambotV2;

import com.yugen.telegrambotV2.Configuration.ApplicationConfig;
import com.yugen.telegrambotV2.Telegram.TelegramBot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
@ComponentScan("com.yugen.telegrambotV2")
public class TelegramBotV2Application {

	public static void main(String[] args) {
		SpringApplication.run(TelegramBotV2Application.class, args);
		try {
			AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationConfig.class);
			TelegramBot telegramBot = ctx.getBean("telegramBot", TelegramBot.class);
			TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
			telegramBotsApi.registerBot(telegramBot);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
