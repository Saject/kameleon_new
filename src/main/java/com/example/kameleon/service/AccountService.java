package com.example.kameleon.service;

import com.example.kameleon.dto.AccountDto;

public interface AccountService {

    AccountDto getByAccountId(Long accountId);

    AccountDto saveAccount(AccountDto account);

    void deleteAccount(Long accountId);

   // AccountDto editAccount(Long accountId, AccountInfoDto accountInfo);

}
