package com.demo.apiproducts.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
   @Column(name = "principal")
   private Boolean principal;
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "id_rl_product")
   private RlProduct product;

   @PrePersist
   public void prePersist() {
      if (provider != null) {
         provider = provider.trim();
      }
      if (providerLink != null) {
         providerLink = providerLink.trim();
      }
   }
}
