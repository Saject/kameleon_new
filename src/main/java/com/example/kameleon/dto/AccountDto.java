package com.example.kameleon.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AccountDto extends AbstractDto {

    private String login;
    private String password;
    private String passwordTo;
    private ProfileInfoDto profileInfo;

}
