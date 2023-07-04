package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "timetablelist") // 対応するテーブル名
public class TimeTableList {

	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; // ID
	
	private String starttime; //開始時刻
	
	private String finishtime; //終了時刻

	private String action;// 行動
	
	private String place;// 場所

	public TimeTableList() {
	}
	
	public TimeTableList(Integer id, String starttime, String finishtime, String action, String place) {
		this.id = id;
		this.starttime = starttime;
		this.finishtime = finishtime;
		this.action = action;
		this.place = place;
	}

	public TimeTableList(String starttime, String finishtime, String action, String place) {
		this.starttime = starttime;
		this.finishtime = finishtime;
		this.action = action;
		this.place = place;
	}
}
