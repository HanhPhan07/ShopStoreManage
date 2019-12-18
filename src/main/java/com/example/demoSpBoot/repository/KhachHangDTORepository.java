package com.example.demoSpBoot.repository;


import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demoSpBoot.dto.KhachHangDTO;
import com.example.demoSpBoot.model.hoadonbanhang;
import com.example.demoSpBoot.model.khachhang;

@Repository
public interface KhachHangDTORepository  extends JpaRepository<KhachHangDTO , String>{

	@Query(value = "SELECT * FROM (SELECT khachhang.makhachhang, khachhang.ten,khachhang.sdt,khachhang.diachi, sum(hoadonbanhang.tonggia) as tonggia, MAX(hoadonbanhang.created_at) as lancuoimuahang, hoadonbanhang.tonggia-hoadonbanhang.khachhangtra-(SELECT sum(sotienthu) FROM `phieuthu` where phieuthu.idhoadon= hoadonbanhang.id ) as tongno FROM hoadonbanhang INNER JOIN khachhang  ON khachhang.makhachhang=hoadonbanhang.makhachhang INNER JOIN phieuthu ON hoadonbanhang.id = phieuthu.idhoadon GROUP BY (khachhang.makhachhang) UNION ALL (SELECT c.makhachhang, c.ten,c.sdt,c.diachi, 0 as tonggia, NULL as lancuoimuahang, 0 as tongno FROM khachhang as c WHERE (c.makhachhang) NOT IN (SELECT h.makhachhang FROM hoadonbanhang as h GROUP BY h.makhachhang))) as temp ORDER BY temp.makhachhang DESC" ,
    		countQuery =  "SELECT COUNT(*) FROM (SELECT * FROM (SELECT khachhang.makhachhang, khachhang.ten,khachhang.sdt,khachhang.diachi, sum(hoadonbanhang.tonggia) as tonggia, MAX(hoadonbanhang.created_at) as lancuoimuahang, hoadonbanhang.tonggia-hoadonbanhang.khachhangtra-(SELECT sum(sotienthu) FROM `phieuthu` where phieuthu.idhoadon= hoadonbanhang.id ) as tongno FROM hoadonbanhang INNER JOIN khachhang  ON khachhang.makhachhang=hoadonbanhang.makhachhang INNER JOIN phieuthu ON hoadonbanhang.id = phieuthu.idhoadon GROUP BY (khachhang.makhachhang) UNION ALL (SELECT c.makhachhang, c.ten,c.sdt,c.diachi, 0 as tonggia, NULL as lancuoimuahang, 0 as tongno FROM khachhang as c WHERE (c.makhachhang) NOT IN (SELECT h.makhachhang FROM hoadonbanhang as h GROUP BY h.makhachhang)))) as temp",nativeQuery = true)
	
	Page<KhachHangDTO> customerListAll(Pageable pageable);
	
	
	
	@Query(value = "SELECT * FROM (SELECT khachhang.makhachhang, khachhang.ten,khachhang.sdt,khachhang.diachi, sum(b.tonggia) as tonggia, MAX(b.created_at) as lancuoimuahang,sum(b.tonggia-b.khachhangtra) as tongno FROM khachhang INNER JOIN hoadonbanhang b ON khachhang.makhachhang=b.makhachhang GROUP BY (khachhang.makhachhang) UNION ALL (SELECT c.makhachhang, c.ten,c.sdt,c.diachi, 0 as tonggia, NULL as lancuoimuahang, 0 as tongno FROM khachhang as c WHERE (c.makhachhang) NOT IN (SELECT h.makhachhang FROM hoadonbanhang as h GROUP BY h.makhachhang))) as temp HAVING temp.ten LIKE %:ten% ORDER BY temp.makhachhang desc" ,
			countQuery =  "SELECT COUNT(*) FROM (SELECT * FROM (SELECT khachhang.makhachhang, khachhang.ten,khachhang.sdt,khachhang.diachi, sum(b.tonggia) as tonggia, MAX(b.created_at) as lancuoimuahang,sum(b.tonggia-b.khachhangtra) as tongno FROM khachhang INNER JOIN hoadonbanhang b ON khachhang.makhachhang=b.makhachhang GROUP BY (khachhang.makhachhang) UNION ALL (SELECT c.makhachhang, c.ten,c.sdt,c.diachi, 0 as tonggia, NULL as lancuoimuahang, 0 as tongno FROM khachhang as c WHERE (c.makhachhang) NOT IN (SELECT h.makhachhang FROM hoadonbanhang as h GROUP BY h.makhachhang))) as temp HAVING temp.ten LIKE %:ten% ) as tqq",nativeQuery = true)
	Page<KhachHangDTO> findCustomerList(Pageable pageable, String ten);
	
	
	
	

}	
