package com.example.demo.model;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import lombok.Data;

@Data
@Component
@SessionScope
public class User {
	//フィールド
		private String name;
		private String password;
		
		//コンストラクタ
		public User(String name, String password) {
			this.name = name;
			this.password = password;
		}

		public User(String name) {
			this.name = name;
		}

}
