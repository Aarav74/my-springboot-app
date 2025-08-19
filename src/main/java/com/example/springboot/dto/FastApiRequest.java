package com.example.springboot.dto;

import java.util.List;

public class FastApiRequest {
    private List<List<Double>> coordinates;

    // Default constructor for JSON deserialization
    public FastApiRequest() {}

    public FastApiRequest(List<List<Double>> coordinates) {
        this.coordinates = coordinates;
    }

    public List<List<Double>> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<List<Double>> coordinates) {
        this.coordinates = coordinates;
    }
}
