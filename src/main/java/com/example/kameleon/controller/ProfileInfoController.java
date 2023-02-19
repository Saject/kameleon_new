package com.example.kameleon.controller;

import com.example.kameleon.dto.ProfileInfoDto;
import com.example.kameleon.service.ProfileInfoService;
import com.example.kameleon.utils.restutils.RestResult;
import com.example.kameleon.utils.restutils.RestResultUtil;
import com.example.kameleon.utils.restutils.controller.AbstractRestController;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileInfoController extends AbstractRestController {

    @Autowired
    private final ProfileInfoService profileInfoService;

    @GetMapping("/{id}")
    public ResponseEntity<RestResult<ProfileInfoDto>> getProfileInfoById(@PathVariable("id") Long accountInfoId) {
        return RestResultUtil.success(profileInfoService.findProfileInfoById(accountInfoId));
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<RestResult<ProfileInfoDto>> editProfileId(@PathVariable("id") Long accountInfoId, @RequestBody @Valid ProfileInfoDto profileInfoDto) {
        return RestResultUtil.success(profileInfoService.editProfileInfo(accountInfoId, profileInfoDto));
    }

}
