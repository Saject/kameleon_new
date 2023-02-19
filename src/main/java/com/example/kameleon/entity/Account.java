package com.example.kameleon.entity;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "account", schema = "public", catalog = "kameleon")
public class Account extends AbstractEntity {

    @Basic
    @Column(name = "login")
    private String login;

    @Basic
    @Column(name = "password")
    private String password;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    private ProfileInfo profileInfo;

    public void setProfileInfo(ProfileInfo profileInfo) {
        profileInfo.setAccount(this);
        this.profileInfo = profileInfo;
    }

    public ProfileInfo getProfileInfo() {
        return profileInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account that = (Account) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(login, that.login) &&
                Objects.equals(password, that.password);
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, login, password);
    }
}
