package com.example.kameleon.repository;

import com.example.kameleon.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {

    Score getByQuoteIdAndProfileId(Long quoteId, Long profileId);

    Boolean existsByQuoteIdAndProfileId(Long quoteId, Long profileId);

}
