package com.example.demoSpBoot.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demoSpBoot.model.chitiethoadonbh;
import com.example.demoSpBoot.model.hoadonbanhang;
import com.example.demoSpBoot.repository.DetailBHRepository;
import com.example.demoSpBoot.repository.HoadonBHRepository;

@Service
public class HoadonBHService {
	@Autowired
	HoadonBHRepository hoadonBHRepo;
	DetailBHRepository chitietRepo;
	
	public Page<hoadonbanhang> findAll(int pageNumber,int pageSize){
<<<<<<< HEAD
		Sort sortable = Sort.by("makhachhang").ascending();
=======
		Sort sortable = Sort.by("id").descending();
>>>>>>> af2b4a2a1c22463d2fa9bbda9183fcbb72c6fa6b
		Pageable phantrang = (Pageable) PageRequest.of(pageNumber, pageSize, sortable);
		return (Page<hoadonbanhang>) hoadonBHRepo.findAll( phantrang);
	}
	public Optional<hoadonbanhang> findByID(int id) {
        return hoadonBHRepo.findById(id);
    }

	public boolean create(hoadonbanhang bill) {
		if(!hoadonBHRepo.findBymahoadon(bill.getMahoadon()).isPresent()) {
			bill.setCreatedAt(new Date());
			String randomStr= (new Date()).getTime() +"";
			bill.setMahoadon("HD"+randomStr);
			hoadonBHRepo.save(bill);
			return true;
			}
		else return false;
	}

	public boolean update(hoadonbanhang bill) {

		if (!hoadonBHRepo.findById(bill.getId()).isPresent()) {
			return false;
		} else {
			hoadonBHRepo.deletechitiethoadon(bill.getId());
			bill.setUpdatedAt(new Date());
			hoadonBHRepo.update(bill.getGiamgia(),bill.getKhachhang().getMakhachhang(),bill.getKhachhangtra(), bill.getLoaithanhtoan(),bill.getNguoisua(),bill.getTonggia(),bill.getTrangthai(),bill.getUpdatedAt(),bill.getId());
			return true;
		}
	}

	public boolean delete(int id) {

		hoadonbanhang bill = hoadonBHRepo.findById(id).orElse(null);
		if (bill == null) {
			return false;
		} else {
			hoadonBHRepo.deletechitiethoadon(id);
			hoadonBHRepo.delete(id);
			
			return true;
		}
	}
	
	public Page<hoadonbanhang> searchBill( int pageNumber, int pageSize, String searchTerm, Date fromdate, Date todate){
		Sort sortable = Sort.by("id").ascending();
		Pageable phantrang = (Pageable) PageRequest.of(pageNumber, pageSize, sortable);
		java.sql.Date sDate1 = convertUtilToSql(fromdate);
		java.sql.Date sDate2 = convertUtilToSql(todate);
		return (Page<hoadonbanhang>) hoadonBHRepo.findByMahoadonContainingAndCreatedAtBetween(phantrang,searchTerm,sDate1,sDate2);
	}
	
	public Page<hoadonbanhang> searchBillNoDate( int pageNumber, int pageSize, String searchTerm){
		Sort sortable = Sort.by("id").ascending();
		Pageable phantrang = (Pageable) PageRequest.of(pageNumber, pageSize, sortable);
		return (Page<hoadonbanhang>) hoadonBHRepo.findByMahoadonContaining(phantrang,searchTerm);
	}
	
	private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }
	
	
}
