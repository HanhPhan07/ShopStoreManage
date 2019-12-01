package com.example.demoSpBoot.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demoSpBoot.service.UsersService;
import com.example.demoSpBoot.jwt.JwtTokenProvider;
import com.example.demoSpBoot.jwt.LoginRespone;
import com.example.demoSpBoot.model.users;
import com.example.demoSpBoot.jwt.CustomUserDetails;

import org.apache.commons.lang3.RandomStringUtils;

import com.example.demoSpBoot.model.users;


@RestController
@RequestMapping("/ShopStore")
public class UsersController {
	@Autowired
	UsersService usersService;
	@Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;
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
	/* ---------------- USER LOGIN ------------------------ */
	
	@PostMapping("/login")
	public LoginRespone authenticateUser (@RequestParam String manhanvien, @RequestParam(name="password") String pass) throws Exception{
		// Xác thực từ username và password.
		try {
			Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                        manhanvien,
	                        pass
	                )
	        );
			System.out.println(manhanvien);
			SecurityContextHolder.getContext().setAuthentication(authentication);
	        //System.out.print(usersService.passwordEncoder(pass));
	        // Trả về jwt cho người dùng.
			CustomUserDetails user =(CustomUserDetails) authentication.getPrincipal();
	        String jwt = tokenProvider.generateToken(user);
	        return new LoginRespone(jwt,user);
		} 
		catch(BadCredentialsException be) {
			throw new Exception("Incorrect username or password",be);
		}
	}
        

        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        
//		Optional<users> user=usersService.findByMNV(manhanvien);
//		if(user.isPresent()) {
//			String password=user.get().getPassword();
//			boolean valuate = BCrypt.checkpw(pass,password);
//			if(valuate==true) {
//				return new ResponseEntity<>(user.get(), HttpStatus.OK);
//			}
//			else return new ResponseEntity<>(
//                    HttpStatus.UNAUTHORIZED);
//		}
//		else return new ResponseEntity<>(
//                HttpStatus.UNAUTHORIZED);
  
        
	public ResponseEntity<users>login (@RequestParam String manhanvien, @RequestParam(name="password") String pass){
		Optional<users> user=usersService.findByMNV(manhanvien);
		if(user.isPresent()) {
			String salt=user.get().getSalt();
			String password=user.get().getPassword();
			boolean valuate = BCrypt.checkpw(pass,password);
			if(valuate==true) {
				return new ResponseEntity<>(user.get(), HttpStatus.OK);
			}
			else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}
	
	/* ---------------- RANDOM STRING ------------------------ */
	@GetMapping("/random")
	public void randomSalt() {
		String salt=RandomStringUtils.randomAlphabetic(6);
		System.out.println(salt);
		//return salt;
	}
}
