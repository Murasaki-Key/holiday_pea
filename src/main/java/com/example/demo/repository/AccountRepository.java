package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>{

	List<Account> findLikeByName(String name);

	List<Account> findByPassword(String password);
	
	Optional<Account>findByNameAndPassword(String name, String password);

}