package com.courtvision.statsapi.ingestion;

import com.courtvision.statsapi.ingestion.balldontlie.ApiResponse;
import com.courtvision.statsapi.ingestion.balldontlie.BalldontlieClient;
import com.courtvision.statsapi.ingestion.balldontlie.PlayerDto;
import com.courtvision.statsapi.ingestion.balldontlie.TeamDto;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class IngestionService {

    private static final Logger log = LoggerFactory.getLogger(IngestionService.class);

    private final BalldontlieClient client;
    private final IngestionWriter writer;

    public IngestionService(BalldontlieClient client, IngestionWriter writer) {
        this.client = client;
        this.writer = writer;
    }

    public void ingestTeams() {
        List<TeamDto> dtos = client.fetchTeams();
        writer.saveTeams(dtos);
        log.info("Saved {} teams", dtos.size());
    }

    public void ingestPlayers(int maxPages) {
        Integer cursor = null;
        int pages = 0;
        int total = 0;
        do {
            ApiResponse<PlayerDto> page = client.fetchPlayersPage(cursor);
            writer.savePlayers(page.data());
            pages++;
            total += page.data().size();
            cursor = page.meta() == null ? null : page.meta().nextCursor();
            log.info("Saved player page {} ({} players so far)", pages, total);
        } while (cursor != null && (maxPages == 0 || pages < maxPages));
        log.info("Player ingestion finished: {} players in {} pages", total, pages);
    }
}