package com.courtvision.statsapi.ingestion.balldontlie;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TeamDto(
        int id,
        String conference,
        String division,
        String city,
        String name,
        @JsonProperty("full_name") String fullName,
        String abbreviation) {
}