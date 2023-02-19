package com.example.kameleon.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.validation.constraints.Email;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProfileInfoDto extends AbstractDto {

    private String lastName;
    private String firstName;
    private String surName;
    private String tel;
    @Email(message = "this do not email")
    private String email;
    //@JsonManagedReference
    //private AccountDto account;

}
