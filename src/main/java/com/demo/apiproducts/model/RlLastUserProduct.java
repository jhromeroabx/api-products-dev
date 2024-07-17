package com.demo.apiproducts.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Date;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Table(name = "last_user_product")
public class RlLastUserProduct {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id_last_user_product")
   private Long id;
   @Column(name = "id_user")
   private Long idUser;
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "id_rl_product", nullable = false)
   private RlProduct rlProduct;

   @Column(name = "deleted_at")
   private Date deletedAt;
}

