package com.example.kameleon.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
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
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ProfileInfoDto profileInfo;
    @JsonManagedReference
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ScoreDto> score;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long countVoices;

}
