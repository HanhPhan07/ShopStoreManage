package com.example.demoSpBoot.repository;


import org.springframework.data.domain.Pageable;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demoSpBoot.dto.KhachHangDTO;

@Repository
public interface KhachHangDTORepository  extends JpaRepository<KhachHangDTO , String>{

	@Query(value = "SELECT k.makhachhang, k.ten,k.sdt,k.diachi, sum(b.tonggia) as tonggia, MAX(b.created_at) as lancuoimuahang,"
    		+ "sum(b.tonggia-b.khachhangtra) as tongno FROM khachhang as k "
    		+ "INNER JOIN hoadonbanhang b "
    		+ "ON k.makhachhang=b.makhachhang  GROUP BY (k.makhachhang)",
    		countQuery =  "SELECT COUNT(k.makhachhang)"
    	    		+ " FROM khachhang as k "
    	    		+ "INNER JOIN hoadonbanhang b "
    	    		+ "ON k.makhachhang=b.makhachhang  GROUP BY (k.makhachhang)",nativeQuery = true)
	
	Page<KhachHangDTO> customerListAll(Pageable pageable);
	
	@Query(value = "SELECT k.makhachhang, k.ten,k.sdt,k.diachi,sum(b.tonggia) as tonggia , MAX(b.created_at) as lancuoimuahang, "
			+ "sum(b.tonggia-b.khachhangtra) as tongno FROM khachhang as k "
			+ "INNER JOIN hoadonbanhang b "
			+ "ON k.makhachhang=b.makhachhang "
			+ "GROUP BY (k.makhachhang) HAVING k.ten LIKE %:ten% ",
			countQuery =  "SELECT COUNT(*) FROM ("
					+ "SELECT k.ten FROM khachhang as k INNER JOIN hoadonbanhang as b "
					+ "ON k.makhachhang=b.makhachhang GROUP BY (k.makhachhang) "
					+ "HAVING k.ten LIKE %:ten% ) as temp",nativeQuery = true)
	Page<KhachHangDTO> findCustomerList(Pageable pageable, String ten);
	
	@Query(value = "SELECT k.makhachhang, k.ten,k.sdt,k.diachi,sum(b.tonggia) as tonggia , MAX(b.created_at) as lancuoimuahang, "
		+ "sum(b.tonggia-b.khachhangtra)as tongno FROM khachhang as k "
		+ "INNER JOIN hoadonbanhang b "
		+ "ON k.makhachhang=b.makhachhang "
		+ "GROUP BY (k.makhachhang) HAVING sum(b.tonggia-b.khachhangtra)>0 ",
		countQuery= " SELECT COUNT(*) FROM (SELECT k.makhachhang, k.ten,k.sdt,k.diachi,sum(b.tonggia) as tonggia , MAX(b.created_at) as lancuoimuahang, "
				+ "sum(b.tonggia-b.khachhangtra)as tongno FROM khachhang as k "
				+ "INNER JOIN hoadonbanhang b "
				+ "ON k.makhachhang=b.makhachhang  "
				+ "GROUP BY (k.makhachhang) HAVING sum(b.tonggia-b.khachhangtra)>0) as temp " , nativeQuery = true)
	Page<KhachHangDTO> customerListIndebtedness(Pageable pageable);
				
	
	@Query(value = "SELECT k.makhachhang, k.ten,k.sdt,k.diachi,sum(b.tonggia) as tonggia , MAX(b.created_at) as lancuoimuahang, "
			+ "sum(b.tonggia-b.khachhangtra)as tongno FROM khachhang as k "
			+ "INNER JOIN hoadonbanhang b "
			+ "ON k.makhachhang=b.makhachhang "
			+ "GROUP BY (k.makhachhang) HAVING sum(b.tonggia-b.khachhangtra)=0 ",
			countQuery= " SELECT COUNT(*) FROM (SELECT k.makhachhang, k.ten,k.sdt,k.diachi,sum(b.tonggia) as tonggia , MAX(b.created_at) as lancuoimuahang, "
					+ "sum(b.tonggia-b.khachhangtra)as tongno FROM khachhang as k "
					+ "INNER JOIN hoadonbanhang b "
					+ "ON k.makhachhang=b.makhachhang  "
					+ "GROUP BY (k.makhachhang) HAVING sum(b.tonggia-b.khachhangtra)=0) as temp " , nativeQuery = true)
	Page<KhachHangDTO> customerListUnIndebtedness(Pageable pageable);
	

}	
