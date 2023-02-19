package com.example.kameleon.repository;

import com.example.kameleon.entity.Quote;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {

    @Query(value = "SELECT * " +
            "FROM \"quote\" q  " +
            "ORDER BY random() " +
            "LIMIT 1;", nativeQuery = true)
    Quote getRandomQuote();


    @EntityGraph(value = "quote-entity-graph")
    List<Quote> findAll();

}
