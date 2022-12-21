package com.yugen.telegrambotV2.Youtube.Model;

import java.util.Arrays;
import java.util.stream.Collectors;

public class YoutubeHTMLFormatter {
    public String getVideoName(YoutubeVideoModel youtubeVideoModel) {

        String channelTitle = youtubeVideoModel.getChannelTitle();
        String videoTitle = youtubeVideoModel.getVideoTitle();

        String fullVideoName = String
                .format("%s - %s", channelTitle, videoTitle);

        return fullVideoName + "\n";
    }

    public String addBoldToText(String text) {

        String[] strings = text.split("\n");
        return Arrays.stream(strings)
                .map(str -> "<b>" + str + "</b>")
                .collect(Collectors.joining());
    }

    public String addUrlToText(String url, String text) {
        String[] strings = text.split("\n");
        return Arrays.stream(strings)
                .map(str -> "<a href=\"" + url + "\">" + str + "</a>")
                .collect(Collectors.joining());
    }

    public String addMonospaceToText(String text) {
        String[] strings = text.split("\n");
        return Arrays.stream(strings)
                .map(str -> "<code>" + str + "</code>")
                .collect(Collectors.joining());
    }

    public String addItalicToText(String text) {
        String[] strings = text.split("\n");
        return Arrays.stream(strings)
                .map(str -> "<i>" + str + "</i>")
                .collect(Collectors.joining());
    }
}
