package com.example.kameleon.mapper;

import com.example.kameleon.dto.AccountDto;
import com.example.kameleon.entity.Account;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class AccountMapper extends AbstractMapper<Account, AccountDto> {


    @Autowired
    private final ModelMapper mapper;

    @Autowired
    public AccountMapper(ModelMapper mapper) {
        super(Account.class, AccountDto.class);
        this.mapper = mapper;
    }


    @PostConstruct
        public void setupMapper() {
            mapper.createTypeMap(Account.class, AccountDto.class)
                    .setPostConverter(toDtoConverter());
        }

}
