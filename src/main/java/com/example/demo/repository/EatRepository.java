package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Eat;

public interface EatRepository extends JpaRepository<Eat, Integer>{
	List <Eat> findBySeasonid(Integer seasonid);
	@Query("select * from eat where seasonid = ?1 and categoryid = ?2")
	List <Eat> find01(@Param("seasonid") Integer season, @Param("categoryid") Integer food);
}
