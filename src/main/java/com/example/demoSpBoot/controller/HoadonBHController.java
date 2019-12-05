package com.example.demoSpBoot.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import com.example.demoSpBoot.model.hoadonbanhang;
import com.example.demoSpBoot.service.HoadonBHService;

@RestController
@RequestMapping("/ShopStore")
@CrossOrigin(origins = "http://localhost:4200")
public class HoadonBHController {
	@Autowired
	HoadonBHService hoadonService;
	@GetMapping("/billBHs")
	/* ---------------- GET ALL BILL ------------------------ */
	public ResponseEntity<List<hoadonbanhang>> findAllBills() {
		//return new ResponseEntity<ServiceResult>(customerService.findAll(), HttpStatus.OK);
		
		List<hoadonbanhang> listBill= hoadonService.findAll();
		if(listBill.isEmpty()) {
			return new ResponseEntity<List<hoadonbanhang>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<hoadonbanhang>>(listBill, HttpStatus.OK);
	}
	/* ---------------- GET BILL BY ID ------------------------ */
	@GetMapping("/billBHs/{id}")
	
	public ResponseEntity<hoadonbanhang> getBillById(
            @PathVariable("id") int id) {
        Optional<hoadonbanhang> bill = hoadonService.findByID(id);

        if (!bill.isPresent()) {
            return new ResponseEntity<>(bill.get(),
                    HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(bill.get(), HttpStatus.OK);
    }

	/* ---------------- CREATE NEW BILL ------------------------ */
	@PostMapping("/billBHs")
	public ResponseEntity<hoadonbanhang> saveBill(@Valid @RequestBody hoadonbanhang bill) {
		if(hoadonService.create(bill)) return new ResponseEntity<hoadonbanhang>(bill,HttpStatus.OK);
		else {
			return new ResponseEntity<hoadonbanhang>(bill,HttpStatus.NOT_FOUND);
		}
		
	}
	
	/* ---------------- UPDATE BILL ------------------------ */
	@PutMapping("/billBHs")

	public ResponseEntity<hoadonbanhang> updateBill(@RequestBody hoadonbanhang bill) {
		if(hoadonService.update(bill)) return new ResponseEntity<hoadonbanhang>(bill,HttpStatus.OK);
		else {
			return new ResponseEntity<hoadonbanhang>(bill,HttpStatus.NOT_FOUND);
		}
	}
	/* ---------------- DELETE CATE ------------------------ */
	
	@DeleteMapping("/billBHs/{id}")
	public ResponseEntity<hoadonbanhang> deleteCate(@PathVariable("id") int id) {
		if(hoadonService.delete(id)) return new ResponseEntity<hoadonbanhang>(HttpStatus.OK);
		else {
			return new ResponseEntity<hoadonbanhang>(HttpStatus.NOT_FOUND);
		}
	}
}
