package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import entity.Product;
import repository.ProductRepository;

@Controller
@RequestMapping("/products/")
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping("showForm")
	public String showProductForm(Product product) {
		return "add-product";
	}
	
	@GetMapping("list")
	public String products(Model model) {
		model.addAttribute("products", this.productRepository.findAll());
		return "index";
	}
	
	@PostMapping("add")
	public String addProduct(@Valid Product product, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "add-product";
		}
		
		this.productRepository.save(product);
		return "redirect:list";
	}
	
	@GetMapping("edit/{productID}")
	public String showUpdateForm(@PathVariable ("productID") long productID, Model model) {
		Product product = this.productRepository.findById(productID)
				.orElseThrow(() -> new IllegalArgumentException("Invalid product id : " +productID));
	
		model.addAttribute("product", product);
		return "update-product";
		
	}
	
	@GetMapping("update/{productID}")
	public String updateProduct(@PathVariable("productID") long productID, @Valid Product product, BindingResult result, Model model) {
		if(result.hasErrors()) {
			product.setProductID(productID);
			return "update-product";
		}
		
		productRepository.save(product);
		model.addAttribute("products", this.productRepository.findAll());
		return "index";
		
	}
	
	
	@DeleteMapping("delete/{product}")
	public String deleteProduct(@PathVariable ("productID") long productID, Model model) {
		Product product = this.productRepository.findById(productID)
				.orElseThrow(() -> new IllegalArgumentException("Invalid product id : " +productID));
		
		this.productRepository.delete(product);
		model.addAttribute("products", this.productRepository.findAll());
		return "index";
	}
}
