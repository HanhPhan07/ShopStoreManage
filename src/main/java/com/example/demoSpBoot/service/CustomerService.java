package com.example.demoSpBoot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demoSpBoot.model.khachhang;
import com.example.demoSpBoot.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	CustomerRepository customerrepository;
	
	public java.util.List<khachhang> findListAll(){
		return  customerrepository.customerListAll();
	}
	
	public Optional<khachhang> findByMNV(String makhachhang) {
        return customerrepository.findById(makhachhang);
    }

	public boolean create(khachhang customer) {
		if(customerrepository.findById(customer.getMakhachhang()).isPresent()) {
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
			customerrepository.delete(customer);
			return true;
		}
	}
	
	public java.util.List<khachhang> findKhachHangByTenLike(String keyword) {
		return customerrepository.findCustomerList(keyword);
	}
	
	public long count(){
		long count;
		count=customerrepository.count();
		if(count>0) return count;
			else {
				return 0;
			}
	}
	
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
