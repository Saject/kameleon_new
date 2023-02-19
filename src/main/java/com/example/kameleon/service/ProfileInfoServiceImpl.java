package com.example.kameleon.service;

import com.example.kameleon.dto.ProfileInfoDto;
import com.example.kameleon.mapper.ProfileInfoMapper;
import com.example.kameleon.repository.ProfileInfoRepository;
import com.example.kameleon.utils.errors.ErrorCode;
import com.example.kameleon.validation.CommonException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ProfileInfoServiceImpl implements ProfileInfoService {

    @Autowired
    private final ProfileInfoRepository profileInfoRepository;
    @Autowired
    private final ProfileInfoMapper profileInfoMapper;

    @Autowired
    private final MessageSource messageSource;

    public ProfileInfoDto findProfileInfoById(Long accountInfoId) {
        return profileInfoMapper.toDto(profileInfoRepository.findById(accountInfoId).orElse(null));
    }

    @Override
    @Transactional
    public ProfileInfoDto editProfileInfo(Long accountInfoId, ProfileInfoDto profileInfoDto) {
        if (!profileInfoRepository.existsById(accountInfoId)) {
            throw new CommonException(ErrorCode.PROFILE_NOT_FOUND.setMessageSource(messageSource));
        }
            profileInfoDto.setId(accountInfoId);
            return profileInfoMapper.toDto(profileInfoRepository.save(profileInfoMapper.toEntity(profileInfoDto)));
        }

}


