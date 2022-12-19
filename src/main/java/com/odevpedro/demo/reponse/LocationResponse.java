package com.odevpedro.demo.reponse;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class LocationResponse {
    private String name;
    private String type;
    private List<String> residents;
    private String url;
}
