package com.courtvision.statsapi.ingestion.balldontlie;

import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class BalldontlieClient {

    private final RestClient restClient;
    private final RateLimiter rateLimiter;

    public BalldontlieClient(BalldontlieProperties properties) {
        this.restClient = RestClient.builder()
                .baseUrl(properties.baseUrl())
                .defaultHeader("Authorization", properties.apiKey())
                .build();
        this.rateLimiter = new RateLimiter(properties.requestsPerMinute());
    }

    public List<TeamDto> fetchTeams() {
        rateLimiter.acquire();
        ApiResponse<TeamDto> response = restClient.get()
                .uri("/v1/teams")
                .retrieve()
                .body(new ParameterizedTypeReference<ApiResponse<TeamDto>>() {
                });
        return response.data();
    }

    public ApiResponse<PlayerDto> fetchPlayersPage(Integer cursor) {
        rateLimiter.acquire();
        return restClient.get()
                .uri(builder -> {
                    builder.path("/v1/players").queryParam("per_page", 100);
                    if (cursor != null) {
                        builder.queryParam("cursor", cursor);
                    }
                    return builder.build();
                })
                .retrieve()
                .body(new ParameterizedTypeReference<ApiResponse<PlayerDto>>() {
                });
    }
}