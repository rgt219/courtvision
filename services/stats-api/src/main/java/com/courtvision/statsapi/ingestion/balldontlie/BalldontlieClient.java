package com.courtvision.statsapi.ingestion.balldontlie;

import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class BalldontlieClient {

    private final RestClient restClient;

    public BalldontlieClient(BalldontlieProperties properties) {
        this.restClient = RestClient.builder()
                .baseUrl(properties.baseUrl())
                .defaultHeader("Authorization", properties.apiKey())
                .build();
    }

    public List<TeamDto> fetchTeams() {
        ApiResponse<TeamDto> response = restClient.get()
                .uri("/v1/teams")
                .retrieve()
                .body(new ParameterizedTypeReference<ApiResponse<TeamDto>>() {
                });
        return response.data();
    }
}