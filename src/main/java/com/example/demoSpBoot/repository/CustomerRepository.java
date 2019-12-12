package com.example.demoSpBoot.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demoSpBoot.model.khachhang;




@Repository
public interface CustomerRepository extends JpaRepository<khachhang , String>{
	
	@Modifying
	@Transactional
    @Query(value = "DELETE FROM hoadonbanhang WHERE makhachhang= :id ",nativeQuery = true)
    void deleteHDBH(String id);

	    
}