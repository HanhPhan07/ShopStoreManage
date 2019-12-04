package com.example.demoSpBoot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demoSpBoot.model.hoadonnhaphang;

public interface HoadonNHRepository extends JpaRepository<hoadonnhaphang, Integer>{
	Optional<hoadonnhaphang> findBymahoadon(String mahoadon);
}
