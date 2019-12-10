package com.example.demoSpBoot.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ResponseEntity<Page<KhachHangDTO>> findAllCustomers(@RequestParam int pageNumber, @RequestParam int pageSize) {	
		Page<KhachHangDTO> listCustomers = customerService.findListAll(pageNumber, pageSize);
		if(listCustomers.isEmpty()) {
			return new ResponseEntity<Page<KhachHangDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Page<KhachHangDTO>>(listCustomers, HttpStatus.OK);
	}
	
	@GetMapping("/allcustomers")
	public ResponseEntity<List<khachhang>> getAllCustomers() {	
		List<khachhang> listCustomers = customerService.getListAllNonPage();
		if(listCustomers.isEmpty()) {
			return new ResponseEntity<List<khachhang>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<khachhang>>(listCustomers, HttpStatus.OK);
	}
	
	/* ---------------- GET CUSTOMER BY NAME ------------------------ */
	@GetMapping("/customers/sreach")
	
	public ResponseEntity<Page<KhachHangDTO>> findCustomersList(@RequestParam int pageNumber, @RequestParam int pageSize,@RequestParam String searchTerm) {
        Page<KhachHangDTO> list = customerService.findByName(pageNumber, pageSize,searchTerm);
        if (list.isEmpty()) {
            return new ResponseEntity<Page<KhachHangDTO>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Page<KhachHangDTO>>(list,HttpStatus.OK);
    }
	
	/* ----------------CUSTOMERS (INDEBTEDNESS)NỢ ------------------------ */ 
	@GetMapping("/customers/indebtedness")
	
	public ResponseEntity<Page<KhachHangDTO>> findCustomersListIndebtedness(@RequestParam int pageNumber, @RequestParam int pageSize) {
        Page<KhachHangDTO> list = customerService.findCustomersIndebtedness(pageNumber, pageSize);
        if (list.isEmpty()) {
        	System.out.print(pageNumber);
        	System.out.print(pageSize);
            //return new ResponseEntity<Page<KhachHangDTO>>(HttpStatus.NO_CONTENT);
            
        }
        return new ResponseEntity<Page<KhachHangDTO>>(list,HttpStatus.OK);
    }
	
	/* ----------------CUSTOMERS (UNINDEBTEDNESS) KHÔNG NỢ ------------------------ */ 
	@GetMapping("/customers/unindebtedness")
	
	public ResponseEntity<Page<KhachHangDTO>> findCustomersListUnIndebtedness(@RequestParam int pageNumber, @RequestParam int pageSize) {
        Page<KhachHangDTO> list = customerService.findCustomersUnIndebtedness(pageNumber, pageSize);
        if (list.isEmpty()) {
        	System.out.print(pageNumber);
        	System.out.print(pageSize);
            //return new ResponseEntity<Page<KhachHangDTO>>(HttpStatus.NO_CONTENT);
            
        }
        return new ResponseEntity<Page<KhachHangDTO>>(list,HttpStatus.OK);
    }

	
	/* ---------------- CREATE NEW CUSTOMER ------------------------ */
	@PostMapping("/customers")
	public ResponseEntity<khachhang> saveCustomer(@Valid @RequestBody khachhang customer){
		if(customerService.create(customer)) return new ResponseEntity<khachhang>(customer,HttpStatus.OK);
		else {
			return new ResponseEntity<khachhang>(customer,HttpStatus.NOT_FOUND);
		}
		
	}
	
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

//	@GetMapping("/customers/count")
//	public long countCustomers() {
//		 return customerService.count();
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
