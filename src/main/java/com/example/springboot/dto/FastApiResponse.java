package com.example.springboot.dto;

import java.util.List;

public class FastApiResponse {
    private String map_html;
    private List<List<Double>> road_coordinates;
    public FastApiResponse() {}

    public FastApiResponse(String map_html, List<List<Double>> road_coordinates) {
        this.map_html = map_html;
        this.road_coordinates = road_coordinates;
    }

    public String getMap_html() {
        return map_html;
    }

    public void setMap_html(String map_html) {
        this.map_html = map_html;
    }

    public List<List<Double>> getRoad_coordinates() {
        return road_coordinates;
    }

    public void setRoad_coordinates(List<List<Double>> road_coordinates) {
        this.road_coordinates = road_coordinates;
    }
}
