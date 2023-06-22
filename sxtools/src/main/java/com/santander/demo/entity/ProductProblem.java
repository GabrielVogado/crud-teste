package com.santander.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "product_problem")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductProblem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "problem_id")
    private Problem problem;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "activation_date")
    private LocalDateTime activationDate;

    @Column(name = "is_active")
    private Boolean isActive;
}
