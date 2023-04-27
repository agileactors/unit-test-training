package com.agileactors.training.domain;

import com.vladmihalcea.hibernate.type.array.ListArrayType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@TypeDefs({
        @TypeDef(
                name = "list-array",
                typeClass = ListArrayType.class
        )
})
public class Trainer {

    @Id
    @Column(name = "trainer_id")
    private UUID id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Type(type = "list-array")
    @Column(name = "rates")
    private List<Integer> rates;

    public double getAvgRate() {
        if (rates == null) {
            return 0;
        }
        return rates.stream()
                .mapToDouble(a -> a)
                .average().orElse(0);
    }

    public void addRate(Integer newRate) {
        if (rates == null) {
            rates = new ArrayList<>();
        }
        rates.add(newRate);
    }
}
