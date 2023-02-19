package com.example.kameleon.service;

import com.example.kameleon.entity.Quote;
import com.example.kameleon.entity.Score;
import com.example.kameleon.repository.QuoteRepository;
import com.example.kameleon.repository.ScoreRepository;
import com.example.kameleon.utils.errors.ErrorCode;
import com.example.kameleon.validation.CommonException;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private final ScoreRepository scoreRepository;

    @Autowired
    private final QuoteRepository quoteRepository;

    @Autowired
    private final MessageSource messageSource;

    @Override
    public void plusVoice(Long quoteId, Long profileId) {
        Quote quote = quoteRepository.findById(quoteId).orElse(null);
        if (quote == null) throw new CommonException(ErrorCode.QUOTE_NOT_FOUND.setMessageSource(messageSource));
        if (BooleanUtils.isTrue(scoreRepository.existsByQuoteIdAndProfileId(quoteId,profileId))) {
            throw new CommonException(ErrorCode.ALREADY_VOTED.setMessageSource(messageSource));
        }
        Score score = new Score();
        score.setQuote(quote);
        score.setProfileId(profileId);
        scoreRepository.save(score);
    }

    @Override
    @Transactional
    public void minusVoice(Long quoteId, Long profileId) {
        Quote quote = quoteRepository.findById(quoteId).orElse(null);
        if (quote == null) throw new CommonException(ErrorCode.QUOTE_NOT_FOUND.setMessageSource(messageSource));
        Score score = scoreRepository.getByQuoteIdAndProfileId(quoteId, profileId);
        if (score == null) throw new CommonException(ErrorCode.VOICE_NOT_FOUND.setMessageSource(messageSource));
        scoreRepository.delete(score);
    }
}
