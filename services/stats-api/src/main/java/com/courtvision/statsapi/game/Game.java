package com.courtvision.statsapi.game;

import com.courtvision.statsapi.team.Team;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer externalId;
    private LocalDate gameDate;
    private Short season;
    private boolean postseason;
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_team_id")
    private Team homeTeam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "visitor_team_id")
    private Team visitorTeam;

    private Short homeTeamScore;
    private Short visitorTeamScore;

    protected Game() {
    }

    public Game(Integer externalId) {
        this.externalId = externalId;
    }

    public Integer getExternalId() {
        return externalId;
    }

    public void setExternalId(Integer externalId) {
        this.externalId = externalId;
    }

    public LocalDate getGameDate() {
        return gameDate;
    }

    public void setGameDate(LocalDate gameDate) {
        this.gameDate = gameDate;
    }

    public Short getSeason() {
        return season;
    }

    public void setSeason(Short season) {
        this.season = season;
    }

    public boolean isPostseason() {
        return postseason;
    }

    public void setPostseason(boolean postseason) {
        this.postseason = postseason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getVisitorTeam() {
        return visitorTeam;
    }

    public void setVisitorTeam(Team visitorTeam) {
        this.visitorTeam = visitorTeam;
    }

    public Short getHomeTeamScore() {
        return homeTeamScore;
    }

    public void setHomeTeamScore(Short homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public Short getVisitorTeamScore() {
        return visitorTeamScore;
    }

    public void setVisitorTeamScore(Short visitorTeamScore) {
        this.visitorTeamScore = visitorTeamScore;
    }

    
}