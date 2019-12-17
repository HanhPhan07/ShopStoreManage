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
	
	public Page<KhachHangDTO> findListAll( int pageNumber,  int pageSize){
		Pageable phantrang = (Pageable) PageRequest.of(pageNumber, pageSize);
		return  (Page<KhachHangDTO>) khachhangDTORes.customerListAll(phantrang);
	}
	
	public Page<KhachHangDTO> findByName(int pageNumber,  int pageSize, String tenkhachhang) {
		Pageable phantrang = (Pageable) PageRequest.of(pageNumber, pageSize);
		return  (Page<KhachHangDTO>) khachhangDTORes.findCustomerList(phantrang, tenkhachhang);
    }
	
	public Optional<khachhang> findBymakhachhang(String makhachhang) {
        return customerrepository.findByMakhachhang(makhachhang);
    }
	
	public List<khachhang> getListAllNonPage(){
		return customerrepository.findAll();
	}

	public boolean create(khachhang customer) {
		if(customerrepository.findById(customer.getMakhachhang()).isPresent()) {
			String randomString=(new Date()).getTime()+"";
			customer.setMakhachhang("KH"+randomString);
			customerrepository.save(customer);
			return true;
		}else return false;
	}
	
	public boolean update(khachhang customer) {

		if (!customerrepository.findById(customer.getMakhachhang()).isPresent()) {
			return false;
		} else {
			customerrepository.save(customer);
			return true;
		}
	}

	public boolean  delete(String id) {

		khachhang customer = customerrepository.findById(id).orElse(null);
		if (customer == null) {
			return false;
		} else {
			customerrepository.deleteHDBH(id);
			customerrepository.delete(customer);
			return true;
		}
	}
	
	
}