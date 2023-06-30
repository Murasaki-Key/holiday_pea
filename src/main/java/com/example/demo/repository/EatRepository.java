package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Eat;

public interface EatRepository extends JpaRepository<Eat, Integer>{
	@Query(
			value="select * from eat where seasonid = ?1 or seasonid = 5",
			nativeQuery=true
		)
	List <Eat> find01(Integer season);
	
	@Query(
			value="select * from eat where "
				+ "(seasonid = ?1 or seasonid = 5) and "
				+ "(categoryid = ?2 or subcategoryid = ?2)",
			nativeQuery=true
		)
	List <Eat> find02(Integer season, Integer food);
}
