package com.example.demo.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.ProductEntity;
import com.example.demo.model.ProductModel;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class ProductController {
@Autowired
   ProductService productService;
  // productform
	//@GetMapping("/productform")	
	public String productForm()
	{
		return "add-product";
	}
	//saveProduct
	//@PostMapping("/saveProduct")
	public String saveProduct(ProductModel productModel) {
		//giving the model object to service layer
		productService.saveProductDetails(productModel);
		return "success";
	}
	// Read the data
	@GetMapping("getallproducts")
	public String getAllProducts(Model model) {
		List<ProductEntity>products=productService.getAllProducts();
		// to send the Products to view layer we need to need to add into Model Object
		model.addAttribute("products",products);
		return "product-list";
	}
	//searchbyid
	@GetMapping("/searchform")
	public String searchform() {
		return "search-product" ;
	}
	
	@PostMapping("/searchById")
	public String searchById(@RequestParam Long id,Model model) {
		ProductEntity product=productService.searchById(id);
		model.addAttribute("product", product);
		return "search-Product";
	}
	//deletebyid
	@GetMapping("/delete/{id}")
	public String deleteProductById(@PathVariable("id") Long id) {
		productService.deleteProductById(id);
		return "redirect:/getallproducts";
	}
	
	@GetMapping("/edit/{id}")
	public String showEditForm(@PathVariable("id") Long id, Model model) {
	    ProductModel product = productService.getProductById(id);

	    if (product == null) {
	        model.addAttribute("errorMessage", "Product not found for ID: " + id);
	        return "error"; // Ensure an error.html page exists in src/main/resources/templates
	    }

	    model.addAttribute("product", product);
	    return "editform";
	}

	@PostMapping("/editproductsave/{id}")
	public String updateProduct(@PathVariable("id") Long id, ProductModel productModel) {
	    productService.updateProduct(id, productModel);
	    return "redirect:/getallproducts"; // Redirects to the list of products after the update
	}
	
	//with default values
	@GetMapping("/productform")
	public String getProductForm(Model model) {
		ProductModel productModel=new ProductModel();
		productModel.setMadeIn("India");
		productModel.setQuantity(2);
		productModel.setDiscountRate(10.5);
		model.addAttribute("productModel",productModel);
		return "add-product";
	}
	
	@PostMapping("/saveProduct")
	public String postMethodName(@Valid ProductModel productModel,BindingResult bindingResult,Model model) {
		HashMap<String,String>validationErrors=new HashMap<String,String>();
		if(bindingResult.hasErrors())
		{
			for(FieldError fieldError: bindingResult.getFieldErrors()) {
				validationErrors.put(fieldError. getField(),fieldError.getDefaultMessage());
			}
			model.addAttribute("validationErrors",validationErrors);
		      return "add-Product";
		} 
		productService.saveProductDetails(productModel);
	       return "redirect:/getallproducts";
	}
}

	

	
	    
	

	
	
	


