package com.courtvision.statsapi.game;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
    Optional<Game> findByExternalId(Integer externalId);
}