package com.example.demoSpBoot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demoSpBoot.model.hoadonbanhang;

@Repository
public interface HoadonBHRepository extends JpaRepository<hoadonbanhang, Integer>{
	//@Query("SELECT e FROM hoadonbanhang e WHERE e.mahoadon = :mahoadon")
	  Optional<hoadonbanhang> findBymahoadon(String mahoadon);
}
