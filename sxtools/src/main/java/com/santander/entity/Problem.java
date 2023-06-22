package com.santander.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@Table(name = "problem")
@AllArgsConstructor
@NoArgsConstructor
public class Problem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "problem", fetch = FetchType.EAGER)
    private List<ProductProblem> productProblems;
}
