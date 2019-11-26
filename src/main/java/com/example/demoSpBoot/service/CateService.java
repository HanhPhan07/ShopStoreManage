package com.example.demoSpBoot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demoSpBoot.model.danhmucsp;
import com.example.demoSpBoot.repository.CateRepository;

@Service
public class CateService {
	@Autowired
	CateRepository cateRepository;
	public List<danhmucsp> findAll(){
		return (List<danhmucsp>) cateRepository.findAll();
	}
}
