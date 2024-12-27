package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ProductEntity;
import com.example.demo.model.ProductModel;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductService {
  
@Autowired
   ProductRepository productRepository;

   public void saveProductDetails(ProductModel productModel) {
	   // To fill the data  to database
	   double taxRate;
	   taxRate=productModel.getPrice()*0.18;
	   
	   double discountPrice;
	   discountPrice=productModel.getPrice()*productModel.getDiscountRate()/100;
	   
	   double offerPrice;
	   offerPrice=productModel.getPrice()-discountPrice;
	   
	   double finalPrice;
	   finalPrice=offerPrice+taxRate  ;
	   
	   double stockPrice;
	   stockPrice=productModel.getPrice()*productModel.getQuantity();
	   
	   ProductEntity productEntity = new ProductEntity();
	   productEntity.setName(productModel.getName());
	   productEntity.setBrand(productModel.getBrand());
	   productEntity.setMadeIn(productModel.getMadeIn());
	   productEntity.setPrice(productModel.getPrice());
	   productEntity.setQuantity(productModel.getQuantity());
	   productEntity.setDiscountRate(productModel.getDiscountRate());
	   productEntity.setTaxRate(taxRate);
	   productEntity.setDiscountPrice(discountPrice);
	   productEntity.setOfferPrice(offerPrice);
	   productEntity.setFinalPrice(finalPrice);
	   productEntity.setStockPrice(stockPrice);
	   
	   productRepository.save(productEntity);
	   }
   // to getallproducts by using findAll
   public List<ProductEntity>getAllProducts(){
	   List<ProductEntity>products=productRepository.findAll();
	   return products; 
   }
   public ProductEntity searchById(Long id) {
	   Optional<ProductEntity>optionalData=productRepository.findById(id);
	   if(optionalData.isPresent()) {
		   ProductEntity product=optionalData.get();
		   return product;
	   }
	   else
	   {
		   return null;
	   }
   }
   //deletebyid
   public void deleteProductById(Long id) {
	   productRepository.deleteById(id);
   }
   //delete
   public ProductModel getProductById(Long id) {
       return productRepository.findById(id)
               .map(productEntity -> {
                   ProductModel productModel = new ProductModel();
                   productModel.setName(productEntity.getName());
                   productModel.setBrand(productEntity.getBrand());
                   productModel.setMadeIn(productEntity.getMadeIn());
                   productModel.setQuantity(productEntity.getQuantity());
                   productModel.setPrice(productEntity.getPrice());
                   productModel.setDiscountRate(productEntity.getDiscountRate());
                   return productModel;
               })
               .orElse(null); // Return null if no product is found
   }
   
   
   public void updateProduct(Long id, ProductModel productModel) {
       ProductEntity Product = productRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Product not found for ID: " + id));

       // Update fields with the data from ProductModel
       Product.setName(productModel.getName());
       Product.setBrand(productModel.getBrand());
       Product.setMadeIn(productModel.getMadeIn());
       Product.setQuantity(productModel.getQuantity());
       Product.setPrice(productModel.getPrice());
       Product.setDiscountRate(productModel.getDiscountRate());

       // Save the updated product
       productRepository.save(Product);
   }
   
   
   
   
   
   
}


   

   

