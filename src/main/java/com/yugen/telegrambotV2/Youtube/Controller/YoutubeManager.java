package com.yugen.telegrambotV2.Youtube.Controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.yugen.telegrambotV2.Configuration.ApplicationConfig;
import com.yugen.telegrambotV2.Youtube.Model.YoutubeHTMLFormatter;
import com.yugen.telegrambotV2.Youtube.Model.YoutubeVideoModel;
import com.yugen.telegrambotV2.Youtube.YoutubeAPI;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;

public class YoutubeManager {

    public SendPhoto getSendPhoto(Message message) {
        String textMessage = message.getText();
        String url = textMessage
                .replaceAll("music\\.", "")
                .replaceAll("&feature=share", "");

        String id;
        if (url.contains("youtube")) {
            id = url.split("v=")[1];
        } else {
            id = url.split("/")[2];
        }

        YoutubeVideoModel youtubeVideoModel = new YoutubeVideoModel(getVideoSnippet(id));
        String response = getTextTemplate(youtubeVideoModel);

        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(message.getChatId());
        sendPhoto.setCaption(response);
        sendPhoto.setParseMode("HTML");
        sendPhoto.setPhoto(new InputFile(youtubeVideoModel.getMaxresThumbnail()));

        return sendPhoto;

    }

    private String getTextTemplate(YoutubeVideoModel youtubeVideoModel) {

        YoutubeHTMLFormatter youtubeHTMLFormatter = new YoutubeHTMLFormatter();
        String url = youtubeVideoModel.getUrl();

        String videoName = "&#127911" + youtubeHTMLFormatter.getVideoName(youtubeVideoModel);
        String genre = youtubeVideoModel.getGenres();
        String albumName = youtubeVideoModel.getAlbumName();
        String releaseDate = youtubeVideoModel.getReleaseDate();
        String channelSignature = "&#9997 MDVIIsRoad";
        String chatChannelSignature = "&#127754 MDVIIs Chat\\Whirlpool";

        String videoNameBold = youtubeHTMLFormatter.addBoldToText(videoName);
        String channelSignatureBold = youtubeHTMLFormatter.addBoldToText(channelSignature);
        String chatChannelSiganutreBold = youtubeHTMLFormatter.addBoldToText(chatChannelSignature);

        String urlVideoName = youtubeHTMLFormatter.addUrlToText(url, videoNameBold);
        String urlChannelSignature = youtubeHTMLFormatter.addUrlToText("https://t.me/MDVIIsRoad", channelSignatureBold);
        String urlChatSignature = youtubeHTMLFormatter.addUrlToText("https://t.me/mdvchatek", chatChannelSiganutreBold);

        return urlVideoName + "\n"
                + youtubeHTMLFormatter.addMonospaceToText(genre) + "\n"
                + urlChannelSignature + " | " + urlChatSignature + "\n"
                + "\n"
                + youtubeHTMLFormatter.addBoldToText("Album: ") + albumName + "\n"
                + youtubeHTMLFormatter.addBoldToText("Released on: ") + releaseDate + "\n"
                + youtubeHTMLFormatter.addItalicToText("Made by #MDVBot");
    }

    private JsonNode getVideoSnippet(String id) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        YoutubeAPI youtubeAPI = ctx.getBean(YoutubeAPI.class);
        return youtubeAPI.getSnippet(id);
    }

}
