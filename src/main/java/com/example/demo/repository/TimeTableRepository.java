package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.TimeTable;

public interface TimeTableRepository extends JpaRepository<TimeTable, Integer>{
}