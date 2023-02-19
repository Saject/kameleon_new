package com.example.kameleon.controller;

import com.example.kameleon.dto.QuoteDto;
import com.example.kameleon.entity.Score;
import com.example.kameleon.service.ScoreService;
import com.example.kameleon.utils.restutils.RestResult;
import com.example.kameleon.utils.restutils.RestResultUtil;
import com.example.kameleon.utils.restutils.controller.AbstractRestController;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/voice")
@RequiredArgsConstructor
public class VoiceController extends AbstractRestController {

    @Autowired
    private final ScoreService scoreService;

    @PostMapping("/plus")
    public ResponseEntity<RestResult> plusVoice(@RequestParam("quoteId") Long quoteId, @RequestParam("profileId") Long profileId) {
        scoreService.plusVoice(quoteId,profileId);
        return RestResultUtil.success();
    }

    @DeleteMapping("/minus")
    public ResponseEntity<RestResult> minusVoice(@RequestParam("quoteId") Long quoteId, @RequestParam("profileId") Long profileId) {
        scoreService.minusVoice(quoteId,profileId);
        return RestResultUtil.success();
    }

}
