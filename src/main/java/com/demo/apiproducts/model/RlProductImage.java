package com.demo.apiproducts.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class RlProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rl_product_image")
    private Long id;

    @Column(name = "provider")
    private String provider;

    @Column(name = "provider_link")
    private String providerLink;

    @Column(name = "deleted_at")
    private Date deletedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rl_product")
    private RlProduct product;

}
