package com.example.kameleon.dto;

import com.example.kameleon.entity.Quote;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ScoreDto extends AbstractDto {

    @JsonBackReference
    private QuoteDto quote;
    private Long profileId;

}
