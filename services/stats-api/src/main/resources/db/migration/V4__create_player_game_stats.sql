CREATE TABLE player_game_stats (
    id              BIGINT    NOT NULL AUTO_INCREMENT,
    player_id       BIGINT    NOT NULL,
    game_id         BIGINT    NOT NULL,
    team_id         BIGINT    NOT NULL,
    seconds_played  INT,
    pts   SMALLINT, reb   SMALLINT, oreb  SMALLINT, dreb  SMALLINT,
    ast   SMALLINT, stl   SMALLINT, blk   SMALLINT, turnover SMALLINT, pf SMALLINT,
    fgm   SMALLINT, fga   SMALLINT, fg3m  SMALLINT, fg3a  SMALLINT,
    ftm   SMALLINT, fta   SMALLINT,
    created_at      TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    CONSTRAINT uq_pgs_player_game UNIQUE (player_id, game_id),
    CONSTRAINT fk_pgs_player FOREIGN KEY (player_id) REFERENCES player (id),
    CONSTRAINT fk_pgs_game   FOREIGN KEY (game_id)   REFERENCES game (id),
    CONSTRAINT fk_pgs_team   FOREIGN KEY (team_id)   REFERENCES team (id),
    INDEX ix_pgs_game (game_id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;