package com.example.demoSpBoot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demoSpBoot.model.hoadonbanhang;
import com.example.demoSpBoot.repository.HoadonBHRepository;

@Service
public class HoadonBHService {
	@Autowired
	HoadonBHRepository hoadonBHRepo;
	public List<hoadonbanhang> findAll(){
		return (List<hoadonbanhang>) hoadonBHRepo.findAll();
	}
	public Optional<hoadonbanhang> findByID(int id) {
        return hoadonBHRepo.findById(id);
    }

	public boolean create(hoadonbanhang bill) {
		if(!hoadonBHRepo.findBymahoadon(bill.getMahoadon()).isPresent()) {
			hoadonBHRepo.save(bill);
			return true;
			}
		else return false;
	}

	public boolean update(hoadonbanhang bill) {

		if (!hoadonBHRepo.findById(bill.getId()).isPresent()) {
			return false;
		} else {
			hoadonBHRepo.save(bill);
			return true;
		}
	}

	public boolean delete(int id) {

		hoadonbanhang bill = hoadonBHRepo.findById(id).orElse(null);
		if (bill == null) {
			return false;
		} else {
			hoadonBHRepo.delete(bill);
			return true;
		}
	}
}
