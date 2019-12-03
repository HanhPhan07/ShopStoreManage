package com.example.demoSpBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demoSpBoot.model.sanpham;

public interface ProductRepository extends JpaRepository<sanpham, Integer>{

}
