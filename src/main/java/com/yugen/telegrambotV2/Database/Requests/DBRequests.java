package com.yugen.telegrambotV2.Database.Requests;

import com.yugen.telegrambotV2.Database.Entity.UpdatesEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class DBRequests {

    private final String address;

    private final RestTemplate restTemplate;

    public DBRequests(String ip, String port) {
        address = "http://" + ip + ":" + port;
        restTemplate = new RestTemplate();
    }

    public void save(UpdatesEntity updates) {
        String url = address + "/save";
        ResponseEntity<UpdatesEntity> responseEntity = restTemplate.postForEntity(url, updates, UpdatesEntity.class);
        responseEntity.getBody();
    }

    public UpdatesEntity getLastUpdate() {
        String url = address + "/last";
        ResponseEntity<UpdatesEntity> responseEntity = restTemplate.getForEntity(url, UpdatesEntity.class);
        return responseEntity.getBody();
    }

    public List getAllUpdates() {
        String url = address + "/last";
        ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);
        return response.getBody();
    }

    public void delete(UpdatesEntity updates) {
        String url = address + "/delete";
        restTemplate.delete(url, updates);
    }

    public void deleteById(int id) {
        String url = address + "/delete";
        restTemplate.delete(url + "/" + id);
    }

    public void deleteAll() {
        String url = address + "/deleteAll";
        restTemplate.delete(url);
    }
}
