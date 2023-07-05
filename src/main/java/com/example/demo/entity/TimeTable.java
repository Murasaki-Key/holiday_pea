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
	
	private Integer planid;//予定id
	
	private String starttime; //開始時刻
	
	private String finishtime; //終了時刻

	private String action;// 行動
	
	private String place;// 場所
	
	private String plandate;// 日付

	public TimeTable() {
	}

	public TimeTable(String username, Integer planid, String starttime, String finishtime, String action, String place,
			String plandate) {
		this.username = username;
		this.planid = planid;
		this.starttime = starttime;
		this.finishtime = finishtime;
		this.action = action;
		this.place = place;
		this.plandate = plandate;
	} 
	
}