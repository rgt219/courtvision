package com.courtvision.statsapi.stats;

import com.courtvision.statsapi.game.Game;
import com.courtvision.statsapi.player.Player;
import com.courtvision.statsapi.team.Team;
import jakarta.persistence.*;

@Entity
@Table(name = "player_game_stats")
public class PlayerGameStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team; // team at the time of this game

    private Integer secondsPlayed;
    private Short pts, reb, oreb, dreb, ast, stl, blk, turnover, pf;
    private Short fgm, fga, fg3m, fg3a, ftm, fta;

    protected PlayerGameStats() {
    }

    public PlayerGameStats(Player player, Game game, Team team) {
        this.player = player;
        this.game = game;
        this.team = team;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Integer getSecondsPlayed() {
        return secondsPlayed;
    }

    public void setSecondsPlayed(Integer secondsPlayed) {
        this.secondsPlayed = secondsPlayed;
    }

    public Short getPts() {
        return pts;
    }

    public void setPts(Short pts) {
        this.pts = pts;
    }

    public Short getReb() {
        return reb;
    }

    public void setReb(Short reb) {
        this.reb = reb;
    }

    public Short getOreb() {
        return oreb;
    }

    public void setOreb(Short oreb) {
        this.oreb = oreb;
    }

    public Short getDreb() {
        return dreb;
    }

    public void setDreb(Short dreb) {
        this.dreb = dreb;
    }

    public Short getAst() {
        return ast;
    }

    public void setAst(Short ast) {
        this.ast = ast;
    }

    public Short getStl() {
        return stl;
    }

    public void setStl(Short stl) {
        this.stl = stl;
    }

    public Short getBlk() {
        return blk;
    }

    public void setBlk(Short blk) {
        this.blk = blk;
    }

    public Short getTurnover() {
        return turnover;
    }

    public void setTurnover(Short turnover) {
        this.turnover = turnover;
    }

    public Short getPf() {
        return pf;
    }

    public void setPf(Short pf) {
        this.pf = pf;
    }

    public Short getFgm() {
        return fgm;
    }

    public void setFgm(Short fgm) {
        this.fgm = fgm;
    }

    public Short getFga() {
        return fga;
    }

    public void setFga(Short fga) {
        this.fga = fga;
    }

    public Short getFg3m() {
        return fg3m;
    }

    public void setFg3m(Short fg3m) {
        this.fg3m = fg3m;
    }

    public Short getFg3a() {
        return fg3a;
    }

    public void setFg3a(Short fg3a) {
        this.fg3a = fg3a;
    }

    public Short getFtm() {
        return ftm;
    }

    public void setFtm(Short ftm) {
        this.ftm = ftm;
    }

    public Short getFta() {
        return fta;
    }

    public void setFta(Short fta) {
        this.fta = fta;
    }

    
}