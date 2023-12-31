package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.TimeTableList;

public interface TimeTableListRepository extends JpaRepository<TimeTableList, Integer>{
	
	@Query(
			value="select * from timetablelist order by starttime ",
			nativeQuery=true
		)
	List <TimeTableList> findAll1();
	
	@Query(
			value="select * from timetablelist order by starttime limit 1 offset ?1 ",
			nativeQuery=true
		)
	List <TimeTableList> findRow(Integer i);
	
}