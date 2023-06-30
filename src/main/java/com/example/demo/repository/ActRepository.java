package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Act;

public interface ActRepository extends JpaRepository<Act, Integer>{
	
	//おまかせ
	
	//①おまかせ、雨で電車移動
	@Query(
			value="select * from act where (seasonid = ?1 or seasonid =5)"
				+ " and vehicleid = 1"
				+ " and weatherid = 1",
			nativeQuery=true
		)
	List <Act> find01(Integer season);
	//②おまかせ、晴れで電車移動
	@Query(
			value="select * from act where (seasonid = ?1 or seasonid =5)"
				+ " and vehicleid = 1",
			nativeQuery=true
		)
	List <Act> find02(Integer season);
	//③おまかせ、雨で車移動
	@Query(
			value="select * from act where (seasonid = ?1 or seasonid =5)"
				+ " and weatherid = 1",
			nativeQuery=true
		)
	List <Act> find03(Integer season);
	//④おまかせ、晴れで車移動
	@Query(
			value="select * from act where (seasonid = ?1 or seasonid =5)",
			nativeQuery=true
		)
	List <Act> find04(Integer season);
	
	//カテゴリー指定
	//⑤カテゴリー指定、雨で電車移動
		@Query(
				value="select * from act where (seasonid = ?1 or seasonid =5)"
					+ " and categoryid = ?2"
					+ " and vehicleid = 1"
					+ " and weatherid = 1",
				nativeQuery=true
			)
		List <Act> find05(Integer season, Integer categoryid);
		//⑥カテゴリー指定、晴れで電車移動
		@Query(
				value="select * from act where (seasonid = ?1 or seasonid =5)"
					+ "and categoryid = ?2"
					+ " and vehicleid = 1",
				nativeQuery=true
			)
		List <Act> find06(Integer season, Integer categoryid);
		//⑦カテゴリー指定、雨で車移動
		@Query(
				value="select * from act where (seasonid = ?1 or seasonid =5)"
					+ "and categoryid = ?2"
					+ " and weatherid = 1",
				nativeQuery=true
			)
		List <Act> find07(Integer season, Integer categoryid);
		//⑧カテゴリー指定、晴れで車移動
		@Query(
				value="select * from act where (seasonid = ?1 or seasonid =5)"
					+ "and categoryid = ?2",
				nativeQuery=true
			)
		List <Act> find08(Integer season, Integer categoryid);
	
}






























