package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Act;

public interface ActRepository extends JpaRepository<Act, Integer>{
	List <Act> findBySeasonid(Integer seasonid);
	@Query(
			value="select * from act where seasonid = ?1 and categoryid = ?2",
			nativeQuery=true
		)
	List <Act> find01(@Param("seasonid") Integer season, @Param("categoryid") Integer act);
}
