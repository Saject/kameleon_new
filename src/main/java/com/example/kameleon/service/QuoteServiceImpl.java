package com.example.kameleon.service;

import com.example.kameleon.dto.QuoteDto;
import com.example.kameleon.entity.ProfileInfo;
import com.example.kameleon.entity.Quote;
import com.example.kameleon.mapper.QuoteMapper;
import com.example.kameleon.repository.ProfileInfoRepository;
import com.example.kameleon.repository.QuoteRepository;
import com.example.kameleon.utils.errors.ErrorCode;
import com.example.kameleon.validation.CommonException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class QuoteServiceImpl implements QuoteService {

    @Autowired
    private final QuoteRepository quoteRepository;
    @Autowired
    private final ProfileInfoRepository profileInfoRepository;
    @Autowired
    private final MessageSource messageSource;
    @Autowired
    private final QuoteMapper quoteMapper;

    @Override
    @Transactional(readOnly = true)
    public QuoteDto findByQuoteId(Long quoteId) {
        return quoteMapper.toDto(quoteRepository.findById(quoteId).orElse(null));
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuoteDto> findByQuoteAll() {
        List<Quote> quotes = quoteRepository.findAll();
        return quotes
                .stream()
                .map(q -> quoteMapper.toDto(q))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public QuoteDto saveQuote(Long profileId, QuoteDto quoteDto) {
        ProfileInfo profileInfo = profileInfoRepository.findById(profileId).orElse(null);
        if (profileInfo ==null) {
            throw new CommonException(ErrorCode.PROFILE_NOT_FOUND.setMessageSource(messageSource));
        }
        Quote quote = quoteMapper.toEntity(quoteDto);
        quote.setProfileInfo(profileInfo);
        return quoteMapper.toDto(quoteRepository.save(quote));
    }

    @Override
    @Transactional
    public void deleteQuote(Long quoteId) {
        quoteRepository.deleteById(quoteId);
    }

    @Override
    public QuoteDto editQuote(Long quoteId, QuoteDto quoteDto) {
        Quote quote = quoteRepository.findById(quoteId).orElse(null);
        if (quote==null) {
            throw new CommonException(ErrorCode.QUOTE_NOT_FOUND.setMessageSource(messageSource));
        }
        quoteDto.setId(quoteId);
        quoteRepository.save(quoteMapper.toEntity(quoteDto));
        return null;
    }

    @Override
    public QuoteDto getRandomQuote() {
        return quoteMapper.toDto(quoteRepository.getRandomQuote());
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuoteDto> getTopQuote() {
        List<Quote> quotes = quoteRepository.findAll()
                .stream()
                .sorted(Comparator.comparingLong(Quote::getCountVoices).reversed())
                .limit(10)
                .collect(Collectors.toList());
        return quotes
                .stream()
                .map(q -> quoteMapper.toDto(q))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuoteDto> getFlopQuote() {
        List<Quote> quotes = quoteRepository.findAll()
                .stream()
                .sorted(Comparator.comparingLong(Quote::getCountVoices))
                .limit(10)
                .collect(Collectors.toList());
        return quotes
                .stream()
                .map(q -> quoteMapper.toDto(q))
                .collect(Collectors.toList());
    }
}
