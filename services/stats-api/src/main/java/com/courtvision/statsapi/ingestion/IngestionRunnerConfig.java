package com.courtvision.statsapi.ingestion;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("ingest")
class IngestionRunnerConfig {

    @Bean
    CommandLineRunner runIngestion(
            IngestionService ingestion,
            @Value("${courtvision.ingestion.max-player-pages:0}") int maxPlayerPages) {
        return args -> {
            ingestion.ingestTeams();
            ingestion.ingestPlayers(maxPlayerPages);
        };
    }
}