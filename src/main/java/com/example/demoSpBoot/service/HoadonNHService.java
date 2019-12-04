package com.example.demoSpBoot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demoSpBoot.model.hoadonnhaphang;
import com.example.demoSpBoot.repository.HoadonNHRepository;

@Service
public class HoadonNHService {
	@Autowired
	HoadonNHRepository hoadonNHRepo;
	public List<hoadonnhaphang> findAll(){
		return (List<hoadonnhaphang>) hoadonNHRepo.findAll();
	}
	public Optional<hoadonnhaphang> findByID(int id) {
        return hoadonNHRepo.findById(id);
    }

	public boolean create(hoadonnhaphang bill) {
		if(!hoadonNHRepo.findBymahoadon(bill.getMahoadon()).isPresent())
		{
			hoadonNHRepo.save(bill);
			return true;
		}else return false;
	}

	public boolean update(hoadonnhaphang bill) {

		if (!hoadonNHRepo.findById(bill.getId()).isPresent()) {
			return false;
		} else {
			hoadonNHRepo.save(bill);
			return true;
		}
	}

	public boolean delete(int id) {

		hoadonnhaphang bill = hoadonNHRepo.findById(id).orElse(null);
		if (bill == null) {
			return false;
		} else {
			hoadonNHRepo.delete(bill);
			return true;
		}
	}
}
