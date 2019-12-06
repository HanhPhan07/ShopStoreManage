package com.example.demoSpBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demoSpBoot.dto.KhachHangDTO;

@Repository
public interface KhachHangDTORepository  extends JpaRepository<KhachHangDTO , String>{
	@Query(value = "SELECT k.makhachhang, k.ten,k.sdt,k.diachi, sum(b.tonggia) as tonggia, MAX(b.created_at) as lancuoimuahang,"
    		+ "sum(b.tonggia-b.khachhangtra) as tongno FROM khachhang k "
    		+ "INNER JOIN hoadonbanhang b "
    		+ "ON k.makhachhang=b.makhachhang  GROUP BY (k.makhachhang)", nativeQuery = true)
    java.util.List<KhachHangDTO> customerListAll();
}	
