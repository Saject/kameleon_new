package com.example.kameleon.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "profile_info", schema = "public", catalog = "kameleon")
public class ProfileInfo extends AbstractEntity {


    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "surname")
    private String surName;

    @Column(name = "tel")
    private String tel;

    @Column(name = "email")
    private String email;

    @OneToOne
    @JoinColumn(name = "account_id", nullable = false, updatable = false, unique = true)
    private Account account;

    @OneToMany(mappedBy = "profileInfo", cascade = CascadeType.ALL)
    private List<Quote> quotes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfileInfo that = (ProfileInfo) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(surName, that.surName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, surName);
    }

    }
