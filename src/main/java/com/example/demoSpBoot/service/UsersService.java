package com.example.demoSpBoot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demoSpBoot.repository.UsersRepository;
import com.example.demoSpBoot.model.users;

@Service
public class UsersService {
	@Autowired
	UsersRepository usersrepository;
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

	public void update(users customer) {

		if (!usersrepository.findById(customer.getManhanvien()).isPresent()) {
			System.out.print("Customer Not Found");
		} else {
			usersrepository.save(customer);
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
}
