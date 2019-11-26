package com.example.demoSpBoot.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demoSpBoot.service.UsersService;
import com.example.demoSpBoot.model.users;
import com.example.demoSpBoot.repository.UsersRepository;

@RestController
@RequestMapping("/ShopStore")
public class UsersController {
	@Autowired
	UsersService usersService;
	/* ---------------- GET ALL CUSTOMER ------------------------ */
	@GetMapping("/users")
	public ResponseEntity<List<users>> findAllUsers() {
		//return new ResponseEntity<ServiceResult>(customerService.findAll(), HttpStatus.OK);
		
		List<users> listUser= usersService.findAll();
		if(listUser.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<users>>(listUser, HttpStatus.OK);
	}
	/* ---------------- GET CUSTOMER BY ID ------------------------ */
	@GetMapping("/users/{manhanvien}")
	
	public ResponseEntity<users> getProductById(
            @PathVariable("manhanvien") String manhanvien) {
        Optional<users> product = usersService.findByMNV(manhanvien);

        if (!product.isPresent()) {
            return new ResponseEntity<>(product.get(),
                    HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(product.get(), HttpStatus.OK);
    }

	/* ---------------- CREATE NEW CUSTOMER ------------------------ */
	@PostMapping("/users")
	public void saveCustomer(@Valid @RequestBody users customer) {
		usersService.create(customer);
	}
	
	/* ---------------- UPDATE CUSTOMER ------------------------ */
	@PutMapping("/users")
//	public ResponseEntity<ServiceResult> update(@RequestBody Customer customer) {
//		return new ResponseEntity<ServiceResult>(customerService.update(customer), HttpStatus.OK);
//	}
	public void updateCustomer(@RequestBody users customer) {
		usersService.update(customer);
	}
	/* ---------------- DELETE CUSTOMER ------------------------ */
	
	@DeleteMapping("/users/{manhanvien}")
	public void deleteCustomer(@PathVariable("manhanvien") String manhanvien) {
		usersService.delete(manhanvien);
	}
}
