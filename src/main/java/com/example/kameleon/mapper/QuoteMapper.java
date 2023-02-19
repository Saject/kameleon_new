package com.example.kameleon.mapper;

import com.example.kameleon.dto.AccountDto;
import com.example.kameleon.dto.QuoteDto;
import com.example.kameleon.dto.ScoreDto;
import com.example.kameleon.entity.Account;
import com.example.kameleon.entity.Quote;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuoteMapper extends AbstractMapper<Quote, QuoteDto> {

//    @Override
//    void mapSpecificFields(Quote source, QuoteDto destination) {
//        super.mapSpecificFields(source, destination);
//        List<ScoreDto> scoreDtoList = source.getScore()
//                .stream()
//                .map(c -> mapper.map(c, ScoreDto.class))
//                .collect(Collectors.toList());
//        destination.setScore(scoreDtoList);
//    }

    @Autowired
    private final ModelMapper mapper;

    @Autowired
    public QuoteMapper(ModelMapper mapper) {
        super(Quote.class, QuoteDto.class);
        this.mapper = mapper;
        //this.personService = personService;
    }


    @PostConstruct
        public void setupMapper() {
            mapper.createTypeMap(Quote.class, QuoteDto.class)
                    .setPostConverter(toDtoConverter());
        }

}
