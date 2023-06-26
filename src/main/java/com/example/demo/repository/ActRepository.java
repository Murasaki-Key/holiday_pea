package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Act;

public interface ActRepository extends JpaRepository<Act, Integer>{
	List <Act> findBySeasonid(Integer seasonid);
}
