package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.TimeTable;
import com.example.demo.model.User;
import com.example.demo.repository.TimeTableRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class MyPageController {
	@Autowired
	HttpSession session;
	
	@Autowired
	User users;
	
	@Autowired
	TimeTableRepository timetableRepository;
	
	@PostMapping("/myplan")
	public String myplan(Model m) {
		String username = users.getName();
		m.addAttribute("username", username);
		
		List <TimeTable> timetable = timetableRepository.decidePlanid(username);
		m.addAttribute("timetable",timetable);
		
		return "mypage";
	}
			
}
