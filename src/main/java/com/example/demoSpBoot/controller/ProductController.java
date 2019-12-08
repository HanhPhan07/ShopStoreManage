package com.example.demoSpBoot.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoSpBoot.model.sanpham;
import com.example.demoSpBoot.service.ProductService;

@RestController
@RequestMapping("/ShopStore")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {
	@Autowired
	ProductService productService;
	
	@GetMapping("/allproducts")
	/* ---------------- GET ALL PRODUCT ------------------------ */
	public ResponseEntity<List<sanpham>> findAllProduct() {
		//return new ResponseEntity<ServiceResult>(customerService.findAll(), HttpStatus.OK);
		
		List<sanpham> listProduct= productService.findAllProd();
		if(listProduct.isEmpty()) {
			return new ResponseEntity<List<sanpham>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<sanpham>>(listProduct, HttpStatus.OK);
	}
	
	@GetMapping("/product")
	/* ---------------- GET ALL PRODUCT ------------------------ */
	public ResponseEntity<Page<sanpham>> findAllProduct(@RequestParam int pageNumber, @RequestParam int pageSize) {
		//return new ResponseEntity<ServiceResult>(customerService.findAll(), HttpStatus.OK);
		
		Page<sanpham> listProduct= productService.findAll(pageNumber,pageSize);
		if(listProduct.isEmpty()) {
			return new ResponseEntity<Page<sanpham>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Page<sanpham>>(listProduct, HttpStatus.OK);
	}
	/* ---------------- GET PRODUCT BY ID ------------------------ */
	@GetMapping("/product/{id}")
	
	public ResponseEntity<sanpham> getProductById(
            @PathVariable("id") int id) {
        Optional<sanpham> product = productService.findByID(id);

        if (!product.isPresent()) {
            return new ResponseEntity<>(product.get(),
                    HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(product.get(), HttpStatus.OK);
    }

	/* ---------------- CREATE NEW PRODUCT ------------------------ */
	@PostMapping("/product")
	public ResponseEntity<sanpham> saveProduct(@Valid @RequestBody sanpham product) {
		if(productService.create(product)) return new ResponseEntity<sanpham>(product,HttpStatus.OK);
		else {
			return new ResponseEntity<sanpham>(product,HttpStatus.NOT_FOUND);
		}
		
	}
	
	/* ---------------- UPDATE PRODUCT ------------------------ */
	@PutMapping("/product")

	public ResponseEntity<sanpham> updateProduct(@RequestBody sanpham product) {
		if(productService.update(product)) return new ResponseEntity<sanpham>(product,HttpStatus.OK);
		else {
			return new ResponseEntity<sanpham>(product,HttpStatus.NOT_FOUND);
		}
	}
	/* ---------------- DELETE PRODUCT ------------------------ */
	
	@DeleteMapping("/product/{id}")
	public ResponseEntity<sanpham> deleteProduct(@PathVariable("id") int id) {
		if(productService.delete(id)) return new ResponseEntity<sanpham>(HttpStatus.OK);
		else {
			return new ResponseEntity<sanpham>(HttpStatus.NOT_FOUND);
		}
	}
}
