package com.example.demoSpBoot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demoSpBoot.model.sanpham;
import com.example.demoSpBoot.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	ProductRepository productRepo;
	public List<sanpham> findAll(){
		return (List<sanpham>) productRepo.findAll();
	}
	public Optional<sanpham> findByID(int id) {
        return productRepo.findById(id);
    }

	public boolean create(sanpham product) {
		if(!productRepo.findBymasp(product.getMasp()).isPresent())
		{
		productRepo.save(product);
			return true;
		}else return false;
	}

	public boolean update(sanpham product) {

		if (!productRepo.findById(product.getId()).isPresent()) {
			return false;
		} else {
			productRepo.save(product);
			return true;
		}
	}

	public boolean delete(int id) {

		sanpham product = productRepo.findById(id).orElse(null);
		if (product == null) {
			return false;
		} else {
			productRepo.delete(product);
			return true;
		}
	}
}
