package com.example.demoSpBoot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demoSpBoot.model.chitiethoadonbh;
import com.example.demoSpBoot.repository.DetailBHRepository;

@Service
public class DetailBHService {
	@Autowired
	DetailBHRepository detailBHRepo;
	public List<chitiethoadonbh> findAll(){
		return (List<chitiethoadonbh>) detailBHRepo.findAll();
	}
	public Optional<chitiethoadonbh> findByID(int id) {
        return detailBHRepo.findById(id);
    }

	public boolean create(chitiethoadonbh bill) {
			detailBHRepo.save(bill);
			return true;
	}
}
