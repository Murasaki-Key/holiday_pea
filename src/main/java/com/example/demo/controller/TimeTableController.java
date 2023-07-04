package com.example.demo.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class TimeTableController {
	@Autowired
	HttpSession session;
	
	@Autowired
	User users;

	@GetMapping("/detail")
	public String detail(
			@RequestParam(name="eatplan",defaultValue= "") String eatplan ,
			@RequestParam(name="actplan",defaultValue="") String actplan ,
			Model m
			) {
		
		Calendar cl = Calendar.getInstance();

        //日付をyyyyMMddの形で出力する
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date = sdf.format(cl.getTime());
		
        m.addAttribute("date",date);
        m.addAttribute("eatplan",eatplan);
        m.addAttribute("actplan",actplan);
        
		return "";
	}
			
}
