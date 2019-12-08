package com.example.demoSpBoot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demoSpBoot.dto.KhachHangDTO;
import com.example.demoSpBoot.model.hoadonbanhang;
import com.example.demoSpBoot.model.khachhang;
import com.example.demoSpBoot.repository.CustomerRepository;
import com.example.demoSpBoot.repository.KhachHangDTORepository;

@Service
public class CustomerService {
	@Autowired
	CustomerRepository customerrepository;
	
	public java.util.List<khachhang> getListAll(){
		return  customerrepository.findAll();
	}
	@Autowired
	KhachHangDTORepository khachhangDTORes;
	public Page<KhachHangDTO> findListAll(@RequestParam int pageNumber, @RequestParam int pageSize){
		Sort sortable = Sort.by("makhachhang").ascending();
		Pageable phantrang = (Pageable) PageRequest.of(pageNumber, pageSize, sortable);
		return  (Page<KhachHangDTO>) khachhangDTORes.customerListAll(phantrang);
	}
	
	public Page<KhachHangDTO> findByName(@RequestParam String tenkhachhang,@RequestParam int pageNumber, @RequestParam int pageSize) {
		Sort sortable = Sort.by("makhachhang").ascending();
		Pageable phantrang = (Pageable) PageRequest.of(pageNumber, pageSize, sortable);
		return  (Page<KhachHangDTO>) khachhangDTORes.findCustomerList(phantrang, tenkhachhang);
    }
	
	public Page<KhachHangDTO> findCustomersIndebtedness(@RequestParam int pageNumber, @RequestParam int pageSize) {
		Sort sortable = Sort.by("makhachhang").ascending();
		Pageable phantrang = (Pageable) PageRequest.of(pageNumber, pageSize, sortable);
		return  (Page<KhachHangDTO>) khachhangDTORes.customerListIndebtedness(phantrang);
    }
	
	public List<khachhang> getListAllNonPage(){
		return customerrepository.findAll();
	}

	public boolean create(khachhang customer) {
		if(customerrepository.findById(customer.getMakhachhang()).isPresent()) {
			customerrepository.save(customer);
			return true;
		}else return false;
	}
//
//	public boolean update(khachhang customer) {
//
//		if (!customerrepository.findById(customer.getMakhachhang()).isPresent()) {
//			return false;
//		} else {
//			customerrepository.save(customer);
//			return true;
//		}
//	}
//
//	public boolean  delete(String id) {
//
//		khachhang customer = customerrepository.findById(id).orElse(null);
//		if (customer == null) {
//			return false;
//		} else {
//			customerrepository.delete(customer);
//			return true;
//		}
//	}
//	
//	public java.util.List<khachhang> findKhachHangByTenLike(String keyword) {
//		return customerrepository.findCustomerList(keyword);
//	}
//	
//	public long count(){
//		long count;
//		count=customerrepository.count();
//		if(count>0) return count;
//			else {
//				return 0;
//			}
//	}
	
//	public Long sumIndebtedness() {
//		return customerrepository.sumIndebtedness();
//	}
//	
//	public Long totalMoney() {
//		if(customerrepository.sumMoney()>0) return customerrepository.sumMoney();
//		else {
//			return (long) 0;
//		}
//	}
	
	
}
