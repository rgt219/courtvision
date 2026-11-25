package com.courtvision.statsapi.ingestion.balldontlie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("smoke")
class SmokeTestConfig {

    private static final Logger log = LoggerFactory.getLogger(SmokeTestConfig.class);

    @Bean
    CommandLineRunner printTeams(BalldontlieClient client) {
        return args -> client.fetchTeams().stream()
                .limit(5)
                .forEach(team -> log.info("Fetched team: {} ({})", team.fullName(), team.abbreviation()));
    }
}