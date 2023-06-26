package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "Accounts") // 対応するテーブル名
public class Account {

	// フィールド
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	private Integer id; // 顧客ID

	@Getter
	private String name; // 顧客名
	
	@Getter
	private String password; //パスワード

	// コンストラクタ
	public Account() {
		
	}

	public Account(Integer id, String name, String password) {
		this.id = id;
		this.name = name;
		this.password = password;
	}
	public Account(String name, String password) {
		this.name = name;
		this.password = password;
		
	}

	public void setName(String names) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
}
