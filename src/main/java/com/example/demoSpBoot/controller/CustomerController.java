package com.example.demoSpBoot.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import com.example.demoSpBoot.dto.KhachHangDTO;
import com.example.demoSpBoot.model.khachhang;
import com.example.demoSpBoot.service.CustomerService;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/ShopStore")
public class CustomerController {
	@Autowired
	CustomerService customerService;
	/* ---------------- GET ALL CUSTOMER ------------------------ */
	@GetMapping("/customers")
	public ResponseEntity<List<KhachHangDTO>> findAllCustomers() {	
		List<KhachHangDTO> listCustomers = customerService.findListAll();
		if(listCustomers.isEmpty()) {
			return new ResponseEntity<List<KhachHangDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<KhachHangDTO>>(listCustomers, HttpStatus.OK);
	}
	@GetMapping("/allcustomers")
	public ResponseEntity<List<khachhang>> getAllCustomers() {	
		List<khachhang> listCustomers = customerService.getListAll();
		if(listCustomers.isEmpty()) {
			return new ResponseEntity<List<khachhang>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<khachhang>>(listCustomers, HttpStatus.OK);
	}
//	
//	/* ---------------- GET CUSTOMER BY ID ------------------------ */
//	@GetMapping("/customers/{manhanvien}")
//	
//	public ResponseEntity<khachhang> getProductById(
//            @PathVariable("makhachhang") String makhachhang) {
//        Optional<khachhang> product = customerService.findByMNV(makhachhang);
//
//        if (!product.isPresent()) {
//            return new ResponseEntity<>(product.get(),
//                    HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(product.get(), HttpStatus.OK);
//    }
//
//	/* ---------------- CREATE NEW CUSTOMER ------------------------ */
//	@PostMapping("/customers")
//	public ResponseEntity<khachhang> saveCustomer(@Valid @RequestBody khachhang customer) {
//		if(customerService.create(customer)) return new ResponseEntity<khachhang>(customer,HttpStatus.OK);
//		else {
//			return new ResponseEntity<khachhang>(customer,HttpStatus.NOT_FOUND);
//		}
//		
//	}
//	
//	/* ---------------- UPDATE CUSTOMER ------------------------ */
//	@PutMapping("/customers")
//
//	public ResponseEntity<khachhang> updateCustomer(@RequestBody khachhang customer) {
//		if(customerService.update(customer)) return new ResponseEntity<khachhang>(customer,HttpStatus.OK);
//		else {
//			return new ResponseEntity<khachhang>(customer,HttpStatus.NOT_FOUND);
//		}
//	}
//	/* ---------------- DELETE CUSTOMER ------------------------ */
//	
//	@DeleteMapping("/customers/{makhachhang}")
//	public ResponseEntity<khachhang> deleteCustomer(@RequestBody String makhachhang) {
//		if(customerService.delete(makhachhang)) return new ResponseEntity<khachhang>(HttpStatus.OK);
//		else {
//			return new ResponseEntity<khachhang>(HttpStatus.NOT_FOUND);
//		}
//	}
//	
//	/* ---------------- SREACH CUSTOMER ------------------------ */
//	@GetMapping("/customers/search/{sreachkeyword}")
//	public ResponseEntity<List<khachhang>> sreachCustomer(@PathVariable("sreachkeyword") String sreachkeyword) {
//		List<khachhang> customer=customerService.findKhachHangByTenLike(sreachkeyword);
//		if(customer.isEmpty()) {
//			return new ResponseEntity<List<khachhang>>(customer,HttpStatus.NO_CONTENT);
//		}
//		else {
//			return new ResponseEntity<List<khachhang>>(customer,HttpStatus.OK);
//		}
//	}
//	@GetMapping("/customers/count")
//	public long countCustomers() {
//		 return customerService.count();
//	}
	
//	@GetMapping("/customers/indebtedness")
//	public ResponseEntity<Long> indebtednessCustomers() {
//		
//		if(customerService.sumIndebtedness()>0) return new ResponseEntity<Long>(customerService.sumIndebtedness(),HttpStatus.OK);
//		else {
//			return new ResponseEntity<Long>(customerService.sumIndebtedness(),HttpStatus.NO_CONTENT);
//		}
//	}
//	
//	@GetMapping("/customers/totalmoney")
//	public ResponseEntity<Long> totalMoney() {
//		if(customerService.totalMoney()>0) return new ResponseEntity<Long>(customerService.totalMoney(),HttpStatus.OK);
//		else {
//			return new ResponseEntity<Long>(customerService.totalMoney(),HttpStatus.NO_CONTENT);
//		}
//	}
	
	
	
}
