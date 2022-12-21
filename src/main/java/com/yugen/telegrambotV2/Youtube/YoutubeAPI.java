package com.yugen.telegrambotV2.Youtube;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.client.RestTemplate;

public class YoutubeAPI {

    private final String token;

    public YoutubeAPI(String token) {
        this.token = token;

    }

    public JsonNode getSnippet(String videoID) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://www.googleapis.com/youtube/v3/videos?"
                +"id=" + videoID
                +"&key=" + token
                + "&part=snippet,topicDetails";

        JsonNode videoListResponse = restTemplate.getForObject(url, JsonNode.class);

        return videoListResponse.get("items").get(0);
    }
}
