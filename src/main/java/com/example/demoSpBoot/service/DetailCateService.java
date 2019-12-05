package com.example.demoSpBoot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demoSpBoot.model.chitietdanhmuc;
import com.example.demoSpBoot.repository.DetailCateRepository;

@Service
public class DetailCateService {
	@Autowired
	DetailCateRepository detailCateRepo;
	public List<chitietdanhmuc> findAll(){
		return (List<chitietdanhmuc>) detailCateRepo.findAll();
	}
	public Optional<chitietdanhmuc> findByID(int id) {
        return detailCateRepo.findById(id);
    }

	public boolean create(chitietdanhmuc detailcate) {
			detailCateRepo.save(detailcate);
			return true;
	}

	public boolean update(chitietdanhmuc detailcate) {

		if (!detailCateRepo.findById(detailcate.getId()).isPresent()) {
			return false;
		} else {
			detailCateRepo.save(detailcate);
			return true;
		}
	}

	public boolean delete(int id) {

		chitietdanhmuc detailcate = detailCateRepo.findById(id).orElse(null);
		if (detailcate == null) {
			return false;
		} else {
			detailCateRepo.delete(detailcate);
			return true;
		}
	}
}
