package com.example.demoSpBoot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demoSpBoot.model.nhacungcap;

public interface NhacungcapRepository extends JpaRepository<nhacungcap, Integer>{
	Optional<nhacungcap> findBymancc(String mancc);
}
