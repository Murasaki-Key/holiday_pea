package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.TimeTableList;
import com.example.demo.model.User;
import com.example.demo.repository.TimeTableListRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class TimeTableController {
	@Autowired
	HttpSession session;
	
	@Autowired
	User users;
	
	@Autowired
	TimeTableListRepository timetablelistRepository;

	@GetMapping("/detail")
	public String detail(
			@RequestParam(name="eatplan",defaultValue= "") String eatplan ,
			@RequestParam(name="actplan",defaultValue="") String actplan ,
			Model m
			) {
		TimeTableList timetablelist = new TimeTableList("11:00","12:00","移動","");
		timetablelistRepository.save(timetablelist);
		
		timetablelist = new TimeTableList("12:00","13:00",eatplan,"");
		timetablelistRepository.save(timetablelist);
		
		timetablelist = new TimeTableList("13:00","14:00","移動","");
		timetablelistRepository.save(timetablelist);
		
		timetablelist = new TimeTableList("14:00","16:00",actplan,"");
		timetablelistRepository.save(timetablelist);
        
		List <TimeTableList> timetables = timetablelistRepository.findAll1();
        
		m.addAttribute("timetables",timetables);
        
        return "timetable";
	}
	
	@PostMapping("/detail/{id}/delete")
	public String delete(
			@PathVariable("id") Integer id,
			Model m
			) {
		
		timetablelistRepository.deleteById(id);
		List <TimeTableList> timetables = timetablelistRepository.findAll1();
        
		m.addAttribute("timetables",timetables);
		return "timetable";
	}
	
	@PostMapping("/detail/{id}/edit")
	public String edit(
			@PathVariable("id") Integer id,
			@RequestParam(name="starttime",defaultValue="") String starttime ,
			@RequestParam(name="finishtime",defaultValue="") String finishtime ,
			@RequestParam(name="action",defaultValue="") String action ,
			@RequestParam(name="place",defaultValue="") String place ,
			Model m
			) {
		
		TimeTableList timetablelist = new TimeTableList(id,starttime,finishtime,action,place);
		timetablelistRepository.save(timetablelist);
		
		List <TimeTableList> timetables = timetablelistRepository.findAll1();
        
		m.addAttribute("timetables",timetables);
		return "timetable";
	}
	
	@PostMapping("/detail/add")
	public String add(
			@RequestParam(name="starttime",defaultValue="") String starttime ,
			@RequestParam(name="finishtime",defaultValue="") String finishtime ,
			@RequestParam(name="action",defaultValue="") String action ,
			@RequestParam(name="place",defaultValue="") String place ,
			Model m) {
		
		TimeTableList timetablelist = new TimeTableList(starttime,finishtime,action,place);
		timetablelistRepository.save(timetablelist);
		
		List <TimeTableList> timetables = timetablelistRepository.findAll1();
        
		m.addAttribute("timetables",timetables);
		
		return "timetable";
	}
			
}
