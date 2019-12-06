package com.example.demoSpBoot.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demoSpBoot.dto.KhachHangDTO;
import com.example.demoSpBoot.model.khachhang;




@Repository
public interface CustomerRepository extends JpaRepository<khachhang , String>{
	
//		@Query("SELECT k.makhachhang, k.ten,k.sdt,k.diachi,sum(b.tonggia), MAX(b.created_at), "
//				+ "sum(b.tonggia-b.khachhangtra) FROM khachhang k "
//				+ "INNER JOIN hoadonbanhang b ON k.makhachhang=b.makhachhang"
//				+ "GROUP BY (k.makhachhang) HAVING k.ten LIKE '%ten%'")
//	    java.util.List<khachhang> findCustomerList(String ten);
//	    
////	    @Query( "SELECT sum(tonggia -khachhangtra) FROM hoadonbanhang  WHERE (tonggia-khachhangtra)>0")
////	    Long sumIndebtedness();
////	    
////	    @Query( "SELECT sum(tonggia) FROM com.example.demoSpBoot.model.hoadonbanhang")
////	    Long sumMoney();
////	    
	    
//
////	    @Query("SELECT k.makhachhang, k.ten,k.sdt,k.diachi,sum(b.tonggia), MAX(b.created_at),"
////	    		+ "sum(b.tonggia-b.khachhangtra) FROM khachhang k "
////	    		+ "INNER JOIN hoadonbanhang b "
////	    		+ "ON k.makhachhang=b.makhachhang  "
////	    		+ "GROUP BY (k.makhachhang) HAVING sum(b.tonggia-b.khachhangtra)>0")
//	    java.util.List<khachhang> customerListIndebtedness();
//	    
////	    @Query("SELECT k.makhachhang, k.ten,k.sdt,k.diachi,sum(b.tonggia), MAX(b.created_at),"
////	    		+ "sum(b.tonggia-b.khachhangtra) FROM khachhang k "
////	    		+ "INNER JOIN hoadonbanhang b "
////	    		+ "ON k.makhachhang=b.makhachhang   "
////	    		+ "GROUP BY (k.makhachhang) HAVING sum(b.tonggia-b.khachhangtra)=0")
//	    java.util.List<khachhang> customerListUnIndebtedness();

	    
}