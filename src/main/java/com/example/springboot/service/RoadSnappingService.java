package com.example.springboot.service;

import com.example.springboot.dto.FastApiRequest;
import com.example.springboot.dto.FastApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class RoadSnappingService {

    private final WebClient webClient;

    public RoadSnappingService(WebClient.Builder webClientBuilder,
                               @Value("${fastapi.service.url}") String fastApiBaseUrl) {
        this.webClient = webClientBuilder.baseUrl(fastApiBaseUrl).build();
    }

    /**
     * Calls the Python FastAPI microservice to get snapped road data.
     * @param coordinates The list of coordinate pairs to send.
     * @return A reactive Mono that will contain the response from the FastAPI service.
     */
    public Mono<FastApiResponse> getSnappedRoadData(List<List<Double>> coordinates) {
        // Create the request body object
        FastApiRequest requestBody = new FastApiRequest(coordinates);

        return webClient.post()
                .uri("/generate-map") // The endpoint on your FastAPI app
                .bodyValue(requestBody) // Send the Java object, WebClient handles JSON serialization
                .retrieve() // Execute the request
                .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
                          response -> response.bodyToMono(String.class).flatMap(errorBody -> {
                              // Log the error and return a wrapped exception
                              System.err.println("Error from FastAPI: " + errorBody);
                              return Mono.error(new RuntimeException("Python service call failed: " + errorBody));
                          }))
                .bodyToMono(FastApiResponse.class); // Deserialize the JSON response back to a Java object
    }
}
