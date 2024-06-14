package com.demo.apiproducts.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;


@Getter
@Setter
@Entity
@Table(name = "rl_product_type")
public class RlProductType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rl_product_type")
    private Long id;
    @Column(name = "description")
    private String description;
    @Column(name = "deleted_at")
    private Date deletedAt;
}
