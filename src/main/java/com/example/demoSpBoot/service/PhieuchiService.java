package com.example.demoSpBoot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demoSpBoot.model.phieuchi;
import com.example.demoSpBoot.repository.PhieuchiRepository;

@Service
public class PhieuchiService {
	@Autowired
	PhieuchiRepository phieuchiRepo;
	public List<phieuchi> findAll(){
		return (List<phieuchi>) phieuchiRepo.findAll();
	}
	public Optional<phieuchi> findByID(int id) {
        return phieuchiRepo.findById(id);
    }

	public boolean create(phieuchi phieuchi) {
		if(!phieuchiRepo.findBymaphieuchi(phieuchi.getMaphieuchi()).isPresent())
		{
		phieuchiRepo.save(phieuchi);
			return true;
		}else return false;
	}

	public boolean update(phieuchi phieuchi) {

		if (!phieuchiRepo.findById(phieuchi.getId()).isPresent()) {
			return false;
		} else {
			phieuchiRepo.save(phieuchi);
			return true;
		}
	}

	public boolean delete(int id) {

		phieuchi phieuchi = phieuchiRepo.findById(id).orElse(null);
		if (phieuchi == null) {
			return false;
		} else {
			phieuchiRepo.delete(phieuchi);
			return true;
		}
	}
}
