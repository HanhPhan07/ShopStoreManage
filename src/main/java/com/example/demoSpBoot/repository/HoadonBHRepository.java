package com.example.demoSpBoot.repository;

import org.springframework.data.domain.Pageable;
import java.sql.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demoSpBoot.model.hoadonbanhang;

@Repository
public interface HoadonBHRepository extends JpaRepository<hoadonbanhang, Integer>{
		
		Page<hoadonbanhang> findAll(Pageable pageable);
		
	//@Query("SELECT e FROM hoadonbanhang e WHERE e.mahoadon = :mahoadon")
	  Optional<hoadonbanhang> findBymahoadon(String mahoadon);
	  
	  Page<hoadonbanhang> findByMahoadonContainingAndCreatedAtBetween(Pageable pageable, String searchTerm, Date fromdate,Date todate);
	  
	  Page<hoadonbanhang> findByMahoadonContaining(Pageable pageable, String searchTerm);
}
