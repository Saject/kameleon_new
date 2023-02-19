package com.example.kameleon.service;

public interface ScoreService {

    void plusVoice(Long quoteId, Long profileId);
    void minusVoice(Long quoteId, Long profileId);
}
