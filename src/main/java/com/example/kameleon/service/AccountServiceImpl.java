package com.example.kameleon.service;

import com.example.kameleon.dto.AccountDto;
import com.example.kameleon.entity.Account;
import com.example.kameleon.mapper.AccountMapper;
import com.example.kameleon.repository.AccountRepository;
import com.example.kameleon.utils.errors.ErrorCode;
import com.example.kameleon.validation.CommonException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    @Autowired
    private final AccountRepository accountRepository;
    @Autowired
    private final AccountMapper accountMapper;
    @Autowired
    private final MessageSource messageSource;



    @Override
    public AccountDto getByAccountId(Long accountId) {
        Account account = accountRepository.findById(accountId).orElse(null);
        if (account == null) throw new CommonException(ErrorCode.ACCOUNT_NOT_FOUND.setMessageSource(messageSource));
        return accountMapper.toDto(account);
    }

    @Override
    @Transactional
    public AccountDto saveAccount(AccountDto account) {
        if (!account.getPassword().equals(account.getPasswordTo())) {
            throw new CommonException(ErrorCode.INVALID_PASSWORD.setMessageSource(messageSource));
        }
        Account ae = accountMapper.toEntity(account);
        return accountMapper.toDto(
                accountRepository.save(ae));
    }

    @Override
    public void deleteAccount(Long accountId) {
        if (!accountRepository.existsById(accountId)) {
            throw new CommonException(ErrorCode.PROFILE_NOT_FOUND.setMessageSource(messageSource));
        }
        accountRepository.deleteById(accountId);
    }

}
