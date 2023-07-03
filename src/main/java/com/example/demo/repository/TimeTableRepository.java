package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Account;

public interface TimeTableRepository extends JpaRepository<Account, Integer>{

	List<Account> findLikeByName(String name);

	List<Account> findByPassword(String password);
	
	List<Account> findByNameAndPassword(String name, String password);

	List<Account> findByName(String name);
	
}