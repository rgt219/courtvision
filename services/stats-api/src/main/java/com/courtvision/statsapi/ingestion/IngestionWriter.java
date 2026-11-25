package com.courtvision.statsapi.ingestion;

import com.courtvision.statsapi.ingestion.balldontlie.PlayerDto;
import com.courtvision.statsapi.ingestion.balldontlie.TeamDto;
import com.courtvision.statsapi.player.Player;
import com.courtvision.statsapi.player.PlayerRepository;
import com.courtvision.statsapi.team.Team;
import com.courtvision.statsapi.team.TeamRepository;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class IngestionWriter {

    private final TeamRepository teams;
    private final PlayerRepository players;

    public IngestionWriter(TeamRepository teams, PlayerRepository players) {
        this.teams = teams;
        this.players = players;
    }

    @Transactional
    public void saveTeams(List<TeamDto> dtos) {
        for (TeamDto dto : dtos) {
            Team team = teams.findByExternalId(dto.id())
                    .orElseGet(() -> new Team(dto.id()));
            team.setAbbreviation(dto.abbreviation());
            team.setCity(dto.city());
            team.setName(dto.name());
            team.setFullName(dto.fullName());
            team.setConference(dto.conference());
            team.setDivision(dto.division());
            teams.save(team);
        }
    }

    @Transactional
    public void savePlayers(List<PlayerDto> dtos) {
        Map<Integer, Team> teamsByExternalId = teams.findAll().stream()
                .collect(Collectors.toMap(Team::getExternalId, Function.identity()));

        for (PlayerDto dto : dtos) {
            Player player = players.findByExternalId(dto.id())
                    .orElseGet(() -> new Player(dto.id()));
            player.setFirstName(dto.firstName());
            player.setLastName(dto.lastName());
            player.setPosition(dto.position());
            player.setHeight(dto.height());
            player.setWeightLbs(parseShort(dto.weight()));
            player.setJerseyNumber(dto.jerseyNumber());
            player.setCollege(dto.college());
            player.setCountry(dto.country());
            player.setDraftYear(toShort(dto.draftYear()));
            player.setDraftRound(toShort(dto.draftRound()));
            player.setDraftNumber(toShort(dto.draftNumber()));
            player.setTeam(dto.team() == null ? null : teamsByExternalId.get(dto.team().id()));
            players.save(player);
        }
    }

    private static Short parseShort(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        try {
            return Short.valueOf(value.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private static Short toShort(Integer value) {
        return value == null ? null : value.shortValue();
    }
}