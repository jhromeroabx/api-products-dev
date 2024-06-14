package com.demo.apiproducts.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "rl_product_image")
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
   @JoinColumn(name = "id_rl_product", nullable = false)
   private RlProduct product;
}
