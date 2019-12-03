package com.example.demoSpBoot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demoSpBoot.model.phieuthu;
import com.example.demoSpBoot.repository.PhieuthuRepository;

@Service
public class PhieuthuService {
	@Autowired
	PhieuthuRepository phieuthuRepo;
	public List<phieuthu> findAll(){
		return (List<phieuthu>) phieuthuRepo.findAll();
	}
	public Optional<phieuthu> findByID(int id) {
        return phieuthuRepo.findById(id);
    }

	public boolean create(phieuthu phieuthu) {
		phieuthuRepo.save(phieuthu);
			return true;
	}

	public boolean update(phieuthu phieuthu) {

		if (!phieuthuRepo.findById(phieuthu.getId()).isPresent()) {
			return false;
		} else {
			phieuthuRepo.save(phieuthu);
			return true;
		}
	}

	public boolean delete(int id) {

		phieuthu phieuthu = phieuthuRepo.findById(id).orElse(null);
		if (phieuthu == null) {
			return false;
		} else {
			phieuthuRepo.delete(phieuthu);
			return true;
		}
	}
}
