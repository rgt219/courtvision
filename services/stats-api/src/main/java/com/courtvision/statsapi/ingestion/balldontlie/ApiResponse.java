package com.courtvision.statsapi.ingestion.balldontlie;

import java.util.List;

public record ApiResponse<T>(List<T> data, Meta meta) {
}