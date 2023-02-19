package com.example.kameleon.entity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "created", updatable = false)
    protected LocalDateTime created;

    @Column(name = "updated", insertable = false)
    protected LocalDateTime updated;


    @PrePersist
    public void toCreate() {
        setCreated(LocalDateTime.now());
    }

    @PreUpdate
    public void toUpdate() {
        setUpdated(LocalDateTime.now());
    }
}