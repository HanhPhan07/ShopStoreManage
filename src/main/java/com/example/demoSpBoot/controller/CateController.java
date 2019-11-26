package com.example.demoSpBoot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoSpBoot.model.danhmucsp;
import com.example.demoSpBoot.service.CateService;

@RestController
@RequestMapping("/ShopStore")
public class CateController {
	@Autowired
	CateService cateService;
	@GetMapping("/cates")
	public ResponseEntity<List<danhmucsp>> findAllCates() {
		//return new ResponseEntity<ServiceResult>(customerService.findAll(), HttpStatus.OK);
		
		List<danhmucsp> listCate= cateService.findAll();
		if(listCate.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<danhmucsp>>(listCate, HttpStatus.OK);
	}
}
