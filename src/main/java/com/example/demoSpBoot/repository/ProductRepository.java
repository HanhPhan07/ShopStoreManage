package com.example.demoSpBoot.repository;

import java.sql.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demoSpBoot.model.sanpham;

public interface ProductRepository extends JpaRepository<sanpham, Integer>{
	Optional<sanpham> findBymasp(String masp);
	Page<sanpham> findAll(Pageable pageable);
	
	@Query( value = "SELECT b.* FROM sanpham b WHERE b.masp=:key OR b.tensp LIKE %:key%",
			countQuery = "SELECT COUNT(*) FROM (SELECT b.* FROM sanpham b WHERE b.masp=:key OR b.tensp LIKE %:key%)",
			nativeQuery = true)
	  
	Page<sanpham> findBymasportensp(Pageable pageable, String key);
}
