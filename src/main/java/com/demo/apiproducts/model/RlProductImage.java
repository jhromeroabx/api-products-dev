package com.demo.apiproducts.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne()
    @JoinColumn(name = "id_rl_product")
    private RlProduct product;

}
