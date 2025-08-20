package com.example.springboot.controller;

import com.example.springboot.dto.FastApiResponse;
import com.example.springboot.service.RoadSnappingService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@Controller
@RequestMapping("/api")
public class MapController {

    private final RoadSnappingService roadSnappingService;

    // A DTO to handle the incoming request from the frontend
    static class FrontendCoordinatesRequest {
        public List<List<Double>> coordinates;

        public List<List<Double>> getCoordinates() {
            return coordinates;
        }

        public void setCoordinates(List<List<Double>> coordinates) {
            this.coordinates = coordinates;
        }
    }

    public MapController(RoadSnappingService roadSnappingService) {
        this.roadSnappingService = roadSnappingService;
    }

    @GetMapping("/")
    public String serveIndex() {
        return "index.html";
    }

    /**
     * NEW: Endpoint to provide sample coordinates.
     * The frontend will call this first.
     */
    @GetMapping("/coordinates")
    @ResponseBody
    public ResponseEntity<List<List<Double>>> provideCoordinates() {
        List<List<Double>> sampleCoordinates = List.of(
            List.of(52.5200, 13.4050),
            List.of(52.5205, 13.4055),
            List.of(52.5210, 13.4060)
        );
        return ResponseEntity.ok(sampleCoordinates);
    }

    /**
     * We'll keep this old endpoint just for demonstration, but the new frontend won't use it.
     */
    @PostMapping("/generate-map")
    @ResponseBody
    public Mono<ResponseEntity<FastApiResponse>> generateMapFromCoords(
            @RequestBody FrontendCoordinatesRequest request) {

        return roadSnappingService.getSnappedRoadData(request.getCoordinates())
                .map(ResponseEntity::ok)
                .onErrorResume(e -> {
                    System.err.println("Error during API call: " + e.getMessage());
                    return Mono.just(ResponseEntity.internalServerError()
                            .body(new FastApiResponse("Error: " + e.getMessage(), null)));
                });
    }
}
