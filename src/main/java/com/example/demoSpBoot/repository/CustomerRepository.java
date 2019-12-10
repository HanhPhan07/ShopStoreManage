package com.example.demoSpBoot.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demoSpBoot.dto.KhachHangDTO;
import com.example.demoSpBoot.model.khachhang;




@Repository
public interface CustomerRepository extends JpaRepository<khachhang , String>{
	
    
   
////	    @Query( "SELECT sum(tonggia -khachhangtra) FROM hoadonbanhang  WHERE (tonggia-khachhangtra)>0")
////	    Long sumIndebtedness();
////	    
////	    @Query( "SELECT sum(tonggia) FROM com.example.demoSpBoot.model.hoadonbanhang")
////	    Long sumMoney();
////	    
//

	    
}