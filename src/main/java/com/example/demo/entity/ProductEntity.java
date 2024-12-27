package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProductEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String name;
   private String brand;
   private String madeIn;
   private double price;
   private int quantity;
   private double discountRate;
   private double taxRate;
   private double discountPrice;
   private double offerPrice;
   private double finalPrice;
   private double stockPrice;
}
