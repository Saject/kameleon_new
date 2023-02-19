package com.example.kameleon.controller;

import com.example.kameleon.dto.QuoteDto;
import com.example.kameleon.service.QuoteService;
import com.example.kameleon.utils.restutils.RestResult;
import com.example.kameleon.utils.restutils.RestResultUtil;
import com.example.kameleon.utils.restutils.controller.AbstractRestController;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quote")
@RequiredArgsConstructor
public class QuoteController extends AbstractRestController {

    @Autowired
    private final QuoteService quoteService;

    @GetMapping("/{id}")
    public ResponseEntity<RestResult<QuoteDto>> getQuoteById(@PathVariable("id") Long quoteId) {
        return RestResultUtil.success(quoteService.findByQuoteId(quoteId));
    }

    @GetMapping("/all")
    public ResponseEntity<RestResult<List<QuoteDto>>> getQuoteAll() {
        return RestResultUtil.success(quoteService.findByQuoteAll());
    }

    @PostMapping("/add")
    public ResponseEntity<RestResult<QuoteDto>> addQuote(@RequestParam("profileId") Long profileId, @RequestBody QuoteDto quoteDto) {
        return RestResultUtil.success(quoteService.saveQuote(profileId,quoteDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RestResult> addQuote(@PathVariable("id") Long quoteId) {
        quoteService.deleteQuote(quoteId);
        return RestResultUtil.success();
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<RestResult<QuoteDto>> editQuote(@PathVariable("id") Long quoteId, @RequestBody QuoteDto quoteDto) {
        return RestResultUtil.success(quoteService.editQuote(quoteId,quoteDto));
    }

    @GetMapping("/random")
    public ResponseEntity<RestResult<QuoteDto>> getQuoteRandom() {
        return RestResultUtil.success(quoteService.getRandomQuote());
    }


    @GetMapping("/top")
    public ResponseEntity<RestResult<QuoteDto>> getTop() {
        return RestResultUtil.success(quoteService.getTopQuote());
    }

    @GetMapping("/flop")
    public ResponseEntity<RestResult<QuoteDto>> getFlop() {
        return RestResultUtil.success(quoteService.getFlopQuote());
    }

}
