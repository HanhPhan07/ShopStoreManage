package com.example.demoSpBoot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demoSpBoot.model.nhasanxuat;
import com.example.demoSpBoot.repository.NhasanxuatRepository;

@Service
public class NhasanxuatService {
	@Autowired
	NhasanxuatRepository nhasxRepo;
	public List<nhasanxuat> findAll(){
		return (List<nhasanxuat>) nhasxRepo.findAll();
	}
	public Optional<nhasanxuat> findByID(int id) {
        return nhasxRepo.findById(id);
    }

	public boolean create(nhasanxuat nsx) {
		nhasxRepo.save(nsx);
			return true;
	}

	public boolean update(nhasanxuat nsx) {

		if (!nhasxRepo.findById(nsx.getId()).isPresent()) {
			return false;
		} else {
			nhasxRepo.save(nsx);
			return true;
		}
	}

	public boolean delete(int id) {

		nhasanxuat nsx = nhasxRepo.findById(id).orElse(null);
		if (nsx == null) {
			return false;
		} else {
			nhasxRepo.delete(nsx);
			return true;
		}
	}
}
