package com.demo.apiproducts.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rl_product")
public class RlProduct {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id_rl_product")
   private Long id;
   @Column(name = "name")
   private String name;
   @Column(name = "description")
   private String description;
   @Column(name = "large_description")
   private String largeDescription;
   @Column(name = "deleted_at")
   private Date deletedAt;
   @Column(name = "currency")
   private Character currency;
   @Column(name = "price")
   private Double price;
   @Column(name = "daily_offer", unique = true)
   private Boolean dailyOffer;
   @ManyToOne()
   @JoinColumn(name = "id_rl_product_type", nullable = false)
   private RlProductType productType;
   @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
   private List <RlProductImage> productImages;
   @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
   private List <RlProductColor> productColors;
}
