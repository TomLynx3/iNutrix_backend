package com.rtu.iNutrix.models.entities;


import com.rtu.iNutrix.models.DTO.Meals.DietGoal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name="`Diet`")
public class Diet extends BaseEntity{

    @Column(name = "created_at", columnDefinition = "timestamptz not null")
    private ZonedDateTime createdAt = ZonedDateTime.now(ZoneOffset.UTC);

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "diet_goal", nullable = false)
    private DietGoal dietGoal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "diet")
    private Set<DietProduct> dietProducts;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "diet")
    private Set<DietHistory> dietHistories;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "diet")
    private Set<DietProgress> dietProgress;
}
