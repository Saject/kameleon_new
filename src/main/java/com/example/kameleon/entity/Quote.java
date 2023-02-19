package com.example.kameleon.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "quote-entity-graph",
                attributeNodes = {
                        @NamedAttributeNode(value = "score")
                }
        )
})
public class Quote extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "profileinfo_id", updatable = false)
    private ProfileInfo profileInfo;

    @OneToMany(mappedBy = "quote", cascade = CascadeType.ALL)
    private List<Score> score;

    @Column(name = "note")
    private String note;

    @Formula(" (SELECT COUNT(*) FROM score as s WHERE s.quote_id = id) ")
    private Long countVoices;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quote quote = (Quote) o;
        return Objects.equals(id, quote.id) &&
                Objects.equals(note, quote.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, note);
    }
}
