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
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "rl_product_colors")
public class RlProductColor {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id_product_color")
   private Long id;
   @Column(name = "description")
   private String description;
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "id_rl_product")
   private RlProduct product;

   @PrePersist
   public void prePersist() {
      if (description != null) {
         description = description.trim();
      }
   }
}
