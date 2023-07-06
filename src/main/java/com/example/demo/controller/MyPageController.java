package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.TimeTable;
import com.example.demo.model.User;
import com.example.demo.repository.TimeTableListRepository;
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
	
	@Autowired
	TimeTableListRepository timetablelistRepository;
	
	@PostMapping("/myplan")
	public String myplan(Model m) {
		String username = users.getName();
		m.addAttribute("username", username);
		
		timetablelistRepository.deleteAll();
		
		List <TimeTable> timetable = timetableRepository.decidePlanid(username);
		m.addAttribute("timetable",timetable);
		Integer emp = 2;
		if(timetable.isEmpty()) {
			emp = 1;
		}
		m.addAttribute("emp",emp);
		return "mypage";
	}
	
	@GetMapping("/myplan/{planid}")
	public String myplans(
			@PathVariable("planid") Integer planid,
			Model m) {
		String username = users.getName();
		
		List <TimeTable> timetable = timetableRepository.find02(username, planid);
		
		String plandate = timetable.get(0).getPlandate();
		
		m.addAttribute("plandate",plandate);
		m.addAttribute("timetable",timetable);
		return "mypagelist";
	}
			
}
