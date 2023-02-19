package com.example.kameleon.mapper;

import com.example.kameleon.dto.ProfileInfoDto;
import com.example.kameleon.entity.ProfileInfo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ProfileInfoMapper extends AbstractMapper<ProfileInfo, ProfileInfoDto> {


    @Autowired
    private final ModelMapper mapper;

    @Autowired
    public ProfileInfoMapper(ModelMapper mapper) {
        super(ProfileInfo.class, ProfileInfoDto.class);
        this.mapper = mapper;
    }


    @PostConstruct
        public void setupMapper() {
            mapper.createTypeMap(ProfileInfo.class, ProfileInfoDto.class)
                    .setPostConverter(toDtoConverter());
        }
}
