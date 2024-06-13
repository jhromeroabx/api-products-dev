package com.demo.apiproducts.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class RlProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rl_product_type")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "productType")
    private List<RlProduct> products;
}
