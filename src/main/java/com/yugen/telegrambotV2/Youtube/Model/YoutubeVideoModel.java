package com.yugen.telegrambotV2.Youtube.Model;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Arrays;

public class YoutubeVideoModel {

    private final JsonNode video;
    private final JsonNode snippet;
    private final JsonNode topicDetails;

    public YoutubeVideoModel(JsonNode video) {

        this.video = video;
        snippet = video.get("snippet");
        topicDetails = video.get("topicDetails");

    }

    public String getUrl() {
        String id = video.get("id").asText();
        return "https://www.youtube.com/watch?v=" + id;

    }

    public String getChannelTitle() {
        return snippet.get("channelTitle").asText().replaceAll("- Topic", "");
    }

    public String getVideoTitle() {
        return snippet.get("title").asText();
    }

    public String getMaxresThumbnail() {
        JsonNode thumbnails = snippet.get("thumbnails");

        if (thumbnails.get("maxres") != null) {
            return thumbnails.get("maxres").get("url").asText();
        } else if (thumbnails.get("standard") != null) {
            return thumbnails.get("standard").get("url").asText();
        } else {
            return thumbnails.get("default").get("url").asText();
        }
    }

    public String getAlbumName() {
        return snippet.get("tags").get(1).asText();
    }

    public String getGenres() {

        JsonNode categories = topicDetails.get("topicCategories");
        StringBuilder genres = new StringBuilder();

        int i = 0;
        while (categories.get(i) != null) {
            String category = categories.get(i).toString();
            String wikiGenre = category.split("wiki/")[1];
            String genre = wikiGenre.replaceAll("Music", "")
                    .replaceAll("_music", "")
                    .replaceAll("_", "&")
                    .replaceAll("\"", "");

            if (genres.length() == 0) {
                genres = new StringBuilder(genre);
            } else if (!category.isEmpty() && !genre.isEmpty()) {
                genres.append(", ").append(genre);
            }
            i++;
        }
        return genres.toString();
    }

    public String getReleaseDate() {

        String description = snippet.get("description").asText();
        String[] des = description.split("\n");

        boolean releasedOnExists = Arrays.stream(des).filter(str -> str.contains("Released on:"))
                .findFirst()
                .map(str -> str.split("on:")[1])
                .isPresent();

        if (releasedOnExists) {
            return Arrays.stream(des).filter(str -> str.contains("Released on:"))
                    .findFirst()
                    .map(str -> str.split("on:")[1])
                    .get();
        } else {
            String publishedAt = snippet.get("publishedAt").toString();
            return publishedAt.split("T")[0].replaceAll("\"", "");
        }
    }
}
