package com.example.demo.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Act;
import com.example.demo.entity.Eat;
import com.example.demo.model.User;
import com.example.demo.repository.ActRepository;
import com.example.demo.repository.EatRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class PlanController {
	@Autowired
	HttpSession session;
	
	@Autowired
	User users;
	
	@Autowired
	EatRepository eatRepository;
	
	@Autowired
	ActRepository actRepository;

	@PostMapping("/plan")
	public String plan(
			@RequestParam(name="season",defaultValue= "") Integer season ,
			@RequestParam(name="weather",defaultValue="") Integer weather ,
			@RequestParam(name="vehicle",defaultValue="") Integer vehicle ,
			@RequestParam(name="food",defaultValue="") Integer food ,
			@RequestParam(name="act",defaultValue="")Integer act ,
			Model m
			) {
		Random random = new Random();
		
		//食べ物をランダムで決定する
		List <Eat> eatlist = null;
		if(food.equals(5) == true) {
			eatlist = eatRepository.findBySeasonid(season);
		}else {
			eatlist = eatRepository.find01(season, food);
		}
		Integer ran1 = eatlist.size();
		Integer randomfood  = random.nextInt(ran1);//乱数生成
		String eatplan =eatlist.get(randomfood).getName();
		
		
		//行動をランダムで決定する
		List <Act> actlist = null;
		if(act.equals(5) == true) {
			actlist = actRepository.findBySeasonid(season);
		}else {
			actlist = actRepository.find01(season, act);
		}
		Integer ran2 = actlist.size();
		Integer randomact  = random.nextInt(ran2);//乱数生成
		String actplan =actlist.get(randomact).getName();
		
		m.addAttribute("season", season);
		m.addAttribute("weather", weather);
		m.addAttribute("vehicle", vehicle);
		m.addAttribute("eatplan", eatplan);
		m.addAttribute("actplan", actplan);
		
		return "confirm";
	}
	
	@PostMapping("/result")
	public String result(
			@RequestParam("season") Integer season ,
			@RequestParam("weather") Integer weather ,
			@RequestParam("vehicle") Integer vehicle ,
			@RequestParam("eatplan") String eatplan ,
			@RequestParam("actplan") String actplan ,
			Model m
			) {
		String message = "";
		
		String name = users.getName();
	
		
		m.addAttribute("name", name);
    	m.addAttribute("message",message);
		m.addAttribute("season", season);
		m.addAttribute("weather", weather);
		m.addAttribute("vehicle", vehicle);
		m.addAttribute("eatplan", eatplan);
		m.addAttribute("actplan", actplan);
		
		
		return "result";
	}
			
}
