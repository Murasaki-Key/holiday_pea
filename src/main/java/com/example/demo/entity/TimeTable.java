package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "timetable") // 対応するテーブル名
public class TimeTable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; // ID
	
	private String username; //ユーザー名
	
	private String plantitele;//予定名
	
	private String starttime; //開始時刻
	
	private String finishtime; //終了時刻

	private String action;// 行動
	
	private String place;// 場所
	
	private String plandate;// 日付

	public TimeTable() {
	} 
}