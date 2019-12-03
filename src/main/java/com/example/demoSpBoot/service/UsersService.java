package com.example.demoSpBoot.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demoSpBoot.repository.UsersRepository;
import com.example.demoSpBoot.jwt.CustomUserDetails;
import com.example.demoSpBoot.model.users;

@Service
public class UsersService implements UserDetailsService{
	@Autowired
	UsersRepository usersrepository;
	@Autowired
    private PasswordEncoder passwordEncoder;
	public List<users> findAll(){
		return (List<users>) usersrepository.findAll();
	}
	
	
	
	public Optional<users> findByMNV(String manhanvien) {
        return usersrepository.findById(manhanvien);
    }

	public boolean create(users customer) {
		if(usersrepository.findById(customer.getManhanvien()).isPresent()) {
			usersrepository.save(customer);
			return true;
		}else return false;
	}

	public boolean update(users customer) {

		if (!usersrepository.findById(customer.getManhanvien()).isPresent()) {
			return false;
		} else {
			usersrepository.save(customer);
			return true;
		}
	}

	public void delete(String id) {

		users customer = usersrepository.findById(id).orElse(null);
		if (customer == null) {
			System.out.print("Customer Not Found");
		} else {
			usersrepository.delete(customer);
			System.out.print("success");
		}
	}


	@Override
    public UserDetails loadUserByUsername(String username) {
        // Kiểm tra xem user có tồn tại trong database không?
        users user = usersrepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(user);
    }
	
	public String passwordEncoder(String password) {
		return passwordEncoder.encode(password);
	}
}
