package com.example.demoSpBoot.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoSpBoot.model.danhmucsp;
import com.example.demoSpBoot.service.CateService;

@RestController
@RequestMapping("/ShopStore")
@CrossOrigin(origins = "http://localhost:4200")
public class CateController {
	@Autowired
	CateService cateService;
	@GetMapping("/cates")
	public ResponseEntity<Page<danhmucsp>> findAllCates(@RequestParam int pageNumber, @RequestParam int pageSize) {
		//return new ResponseEntity<ServiceResult>(customerService.findAll(), HttpStatus.OK);
		
		Page<danhmucsp> listCate= cateService.findAll(pageNumber, pageSize);
		if(listCate.isEmpty()) {
			return new ResponseEntity<Page<danhmucsp>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Page<danhmucsp>>(listCate, HttpStatus.OK);
	}
	/* ---------------- GET CATE BY ID ------------------------ */
	@GetMapping("/cates/{id}")
	
	public ResponseEntity<danhmucsp> getcateById(
            @PathVariable("id") int id) {
        Optional<danhmucsp> danhmuc = cateService.findByID(id);

        if (!danhmuc.isPresent()) {
            return new ResponseEntity<>(danhmuc.get(),
                    HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(danhmuc.get(), HttpStatus.OK);
    }

	/* ---------------- CREATE NEW CATE ------------------------ */
	@PostMapping("/cates")
	public ResponseEntity<danhmucsp> saveCate(@Valid @RequestBody danhmucsp cate) {
		if(cateService.create(cate)) return new ResponseEntity<danhmucsp>(cate,HttpStatus.OK);
		else {
			return new ResponseEntity<danhmucsp>(cate,HttpStatus.NOT_FOUND);
		}
		
	}
	
	/* ---------------- UPDATE CATE ------------------------ */
	@PutMapping("/cates")

	public ResponseEntity<danhmucsp> updateCate(@RequestBody danhmucsp cate) {
		if(cateService.update(cate)) return new ResponseEntity<danhmucsp>(cate,HttpStatus.OK);
		else {
			return new ResponseEntity<danhmucsp>(cate,HttpStatus.NOT_FOUND);
		}
	}
	/* ---------------- DELETE CATE ------------------------ */
	
	@DeleteMapping("/cates/{id}")
	public ResponseEntity<danhmucsp> deleteCate(@PathVariable("id") int id) {
		if(cateService.delete(id)) return new ResponseEntity<danhmucsp>(HttpStatus.OK);
		else {
			return new ResponseEntity<danhmucsp>(HttpStatus.NOT_FOUND);
		}
	}
}
