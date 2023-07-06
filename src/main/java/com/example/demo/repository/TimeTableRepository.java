package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.TimeTable;

public interface TimeTableRepository extends JpaRepository<TimeTable, Integer>{
	@Query(
			value=" select distinct on(planid)* from timetable where username like ?1",
			nativeQuery=true
		)
	List<TimeTable> decidePlanid(String username);
	
	
	List<TimeTable> findDisntinctByUsername(String username);
	
	@Query(
			value=" select  * from timetable  order by planid limit 1 offset ?1",
			nativeQuery=true
		)
	List<TimeTable> decidePlanid1(Integer p);
	
	@Query(
			value=" select  * from timetable where username like ?1 and planid ?2 order by starttime",
			nativeQuery=true
		)
	List<TimeTable> find02(String username, Integer planid);
	
}