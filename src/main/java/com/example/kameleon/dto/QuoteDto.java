package com.example.kameleon.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class QuoteDto extends AbstractDto {

    private String note;
    private ProfileInfoDto profileInfo;
    @JsonManagedReference
    private List<ScoreDto> score;
    private Long countVoices;

    //@JsonManagedReference
    //private AccountDto account;

}
