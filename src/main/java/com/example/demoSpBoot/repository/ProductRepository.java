package com.example.demoSpBoot.repository;

import java.sql.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demoSpBoot.model.sanpham;

public interface ProductRepository extends JpaRepository<sanpham, Integer>{
	Optional<sanpham> findBymasp(String masp);
	Page<sanpham> findAll(Pageable pageable);
	Page<sanpham> findBymaspContainingAndCreatedAtBetween(Pageable pageable, String searchTerm, Date fromdate,Date todate);
	  
	Page<sanpham> findBymaspContaining(Pageable pageable, String searchTerm);
}
