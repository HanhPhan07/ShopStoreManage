package com.example.demoSpBoot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demoSpBoot.model.phieuchi;

public interface PhieuchiRepository extends JpaRepository<phieuchi, Integer>{
	Optional<phieuchi> findBymaphieuchi(String maphieuchi);
}
