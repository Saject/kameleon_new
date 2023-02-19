package com.example.kameleon.service;

import com.example.kameleon.dto.ProfileInfoDto;

public interface ProfileInfoService {

    ProfileInfoDto findProfileInfoById(Long personId);

    ProfileInfoDto editProfileInfo(Long accountInfoId, ProfileInfoDto profileInfoDto);

    //PersonDto saveAccount(PersonEntity personEntity);

}
