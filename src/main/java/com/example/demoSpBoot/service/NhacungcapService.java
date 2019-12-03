package com.example.demoSpBoot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demoSpBoot.model.nhacungcap;
import com.example.demoSpBoot.repository.NhacungcapRepository;

@Service
public class NhacungcapService {
	@Autowired
	NhacungcapRepository nhaccRepo;
	public List<nhacungcap> findAll(){
		return (List<nhacungcap>) nhaccRepo.findAll();
	}
	public Optional<nhacungcap> findByID(int id) {
        return nhaccRepo.findById(id);
    }

	public boolean create(nhacungcap ncc) {
		nhaccRepo.save(ncc);
			return true;
	}

	public boolean update(nhacungcap ncc) {

		if (!nhaccRepo.findById(ncc.getId()).isPresent()) {
			return false;
		} else {
			nhaccRepo.save(ncc);
			return true;
		}
	}

	public boolean delete(int id) {

		nhacungcap ncc = nhaccRepo.findById(id).orElse(null);
		if (ncc == null) {
			return false;
		} else {
			nhaccRepo.delete(ncc);
			return true;
		}
	}
}
