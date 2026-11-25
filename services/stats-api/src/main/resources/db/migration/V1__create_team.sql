CREATE TABLE team (
    id            BIGINT       NOT NULL AUTO_INCREMENT,
    external_id   INT          NOT NULL,
    abbreviation  VARCHAR(5)   NOT NULL,
    city          VARCHAR(50)  NOT NULL,
    name          VARCHAR(50)  NOT NULL,
    full_name     VARCHAR(100) NOT NULL,
    conference    VARCHAR(10),
    division      VARCHAR(20),
    created_at    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    CONSTRAINT uq_team_external_id UNIQUE (external_id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;