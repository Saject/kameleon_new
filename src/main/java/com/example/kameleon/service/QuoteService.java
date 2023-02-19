package com.example.kameleon.service;

import com.example.kameleon.dto.QuoteDto;
import com.example.kameleon.entity.Quote;

import java.util.List;

public interface QuoteService {

    QuoteDto findByQuoteId(Long quoteId);

    List<QuoteDto> findByQuoteAll();

    QuoteDto saveQuote(Long profileId, QuoteDto quoteDto);

    void deleteQuote(Long quoteId);

    QuoteDto editQuote(Long quoteId, QuoteDto quoteDto);

    QuoteDto getRandomQuote();

    List<QuoteDto> getTopQuote();

    List<QuoteDto> getFlopQuote();

}
