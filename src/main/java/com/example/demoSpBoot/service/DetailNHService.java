package com.example.demoSpBoot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demoSpBoot.model.chitiethoadonnh;
import com.example.demoSpBoot.repository.DetailNHRepository;

@Service
public class DetailNHService {
	@Autowired
	DetailNHRepository detailNHRepo;
	public List<chitiethoadonnh> findAll(){
		return (List<chitiethoadonnh>) detailNHRepo.findAll();
	}
	public Optional<chitiethoadonnh> findByID(int id) {
        return detailNHRepo.findById(id);
    }

	public boolean create(chitiethoadonnh bill) {
			detailNHRepo.save(bill);
			return true;
	}
}
