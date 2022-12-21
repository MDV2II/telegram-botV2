package com.yugen.telegrambotV2.YugenBot.Handlers;

import com.yugen.telegrambotV2.Telegram.TelegramExecutor;
import com.yugen.telegrambotV2.Youtube.Controller.YoutubeManager;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class TextHandler {

    private final Message message;

    public TextHandler(Message message) {
        this.message = message;
    }

    public void controlText() {
        String textRequest = message.getText();

        if (textRequest.startsWith("/")) {
            determineCommand(textRequest);
        } else if (textRequest.startsWith("http")) {
            determineUrl(message);
        }

        new TelegramExecutor()
                .execute(new SendMessage(message.getChatId().toString(),
                "Incorrect request in Text"));
    }

    private void determineCommand(String textRequest) {
        SendMessage sendMessage = new SendMessage();

        sendMessage.setChatId(message.getChatId());
        if (textRequest.equalsIgnoreCase("/start")) {
            sendMessage.setText("Hello world");
        } else if (textRequest.equalsIgnoreCase("/getYoutube")) {
            sendMessage.setText("Send http-link");
        }

        new TelegramExecutor().execute(sendMessage);
    }

    private Object determineUrl(Message message) {
        if (message.getText().contains("youtu")) {
            YoutubeManager youtubeManager = new YoutubeManager();
            new TelegramExecutor().execute(youtubeManager.getSendPhoto(message));
        }

        return "url";
    }
}
