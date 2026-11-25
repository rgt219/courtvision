package com.courtvision.statsapi.stats;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerGameStatsRepository extends JpaRepository<PlayerGameStats, Long> {
    Optional<PlayerGameStats> findByPlayerIdAndGameId(Long playerId, Long gameId);
}
