package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Eat;

public interface EatRepository extends JpaRepository<Eat, Integer>{
	List <Eat> findBySeasonid(Integer seasonid);
	@Query(
			value="select * from act where seasonid = ?1 and categoryid = ?2",
			nativeQuery=true
		)
	List <Eat> find01(Integer season, Integer act);
}
