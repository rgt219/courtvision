package com.courtvision.statsapi.ingestion.balldontlie;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Meta(
        @JsonProperty("next_cursor") Integer nextCursor,
        @JsonProperty("per_page") Integer perPage) {
}