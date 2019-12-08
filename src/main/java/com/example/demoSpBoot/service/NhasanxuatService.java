package com.example.demoSpBoot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demoSpBoot.model.nhasanxuat;
import com.example.demoSpBoot.repository.NhasanxuatRepository;

@Service
public class NhasanxuatService {
	@Autowired
	NhasanxuatRepository nhasxRepo;
	public Page<nhasanxuat> findAll(int pageNumber,int pageSize){
		Sort sortable = Sort.by("id").ascending();
		Pageable phantrang = (Pageable) PageRequest.of(pageNumber, pageSize, sortable);
		return (Page<nhasanxuat>) nhasxRepo.findAll( phantrang);
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
