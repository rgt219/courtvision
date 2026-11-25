package com.courtvision.statsapi.ingestion.balldontlie;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "courtvision.balldontlie")
public record BalldontlieProperties(String baseUrl, String apiKey, int requestsPerMinute) {
}