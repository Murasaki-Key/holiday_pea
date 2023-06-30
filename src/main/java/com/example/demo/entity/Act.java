package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "Act") // 対応するテーブル名
public class Act {

	// フィールド
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	private Integer id; // ID
	
	@Getter
	private Integer categoryid; //カテゴリー
	
	@Getter
	private Integer vehicleid; //乗り物
	
	@Getter
	private Integer weatherid; //天気
	
	@Getter
	private Integer seasonid; //季節

	@Getter
	private String name;// やること

	public Act() {
	} 
	
}
