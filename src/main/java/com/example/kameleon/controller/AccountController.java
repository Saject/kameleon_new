package com.example.kameleon.controller;

import com.example.kameleon.dto.AccountDto;
import com.example.kameleon.service.AccountService;
import com.example.kameleon.utils.restutils.RestResult;
import com.example.kameleon.utils.restutils.RestResultUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    @Autowired
    private final AccountService accountService;

    @GetMapping("/{id}")
    public ResponseEntity<RestResult<AccountDto>> getAccountById(@PathVariable("id") Long accountId) {
        return RestResultUtil.success(accountService.getByAccountId(accountId));
    }

    @PostMapping("/register")
    public ResponseEntity<RestResult<AccountDto>> saveAccount(@RequestBody AccountDto account) {
        return RestResultUtil.success(accountService.saveAccount(account));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RestResult> deleteAccount(@PathVariable("id") Long accountId) {
        accountService.deleteAccount(accountId);
        return RestResultUtil.success();
    }

}
