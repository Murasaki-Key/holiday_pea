package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.TimeTable;

public interface TimeTableRepository extends JpaRepository<TimeTable, Integer>{
	@Query(
			value=" select distinct on(planid)* from timetable where username like '?1'",
			nativeQuery=true
		)
	List<TimeTable> decidePlanid(String username);
	
	Integer findDisntinctByUsername(String username);
	
}