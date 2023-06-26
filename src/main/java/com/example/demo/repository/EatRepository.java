package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Eat;

public interface EatRepository extends JpaRepository<Eat, Integer>{
	List <Eat> findBySeasonid(Integer seasonid);

}
