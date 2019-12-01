package com.example.demoSpBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demoSpBoot.model.khachhang;


@Repository
public interface CustomerRepository extends JpaRepository<khachhang, String>{
	
}