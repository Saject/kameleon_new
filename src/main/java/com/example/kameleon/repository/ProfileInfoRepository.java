package com.example.kameleon.repository;

import com.example.kameleon.entity.ProfileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileInfoRepository extends JpaRepository<ProfileInfo,Long> {

}
