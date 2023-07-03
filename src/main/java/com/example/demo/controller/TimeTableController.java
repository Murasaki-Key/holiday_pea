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
public class TimeTableController {
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
		if(food.equals(8) == true) {
			eatlist = eatRepository.find01(season);
		}else {
			eatlist = eatRepository.find02(season, food);
		}
		Integer ran1 = eatlist.size();
		Integer randomfood  = random.nextInt(ran1);//乱数生成
		String eatplan =eatlist.get(randomfood).getName();
		
		
		//行動をランダムで決定する
		List <Act> actlist = null;
		if(act.equals(10) == true) {//おまかせ
			if((vehicle.equals(1) == true) && (weather.equals(2) == true)) {//雨で電車移動
				actlist = actRepository.find01(season);
			}else if(vehicle.equals(1) == true){//晴れで電車移動
				actlist = actRepository.find02(season);
			}else if(weather.equals(2) == true){//雨で車移動
				actlist = actRepository.find03(season);
			}else{//晴れで車移動
				actlist = actRepository.find04(season);
			}
		}else {
			if((vehicle.equals(1) == true) && (weather.equals(2) == true)) {//雨で電車移動
				actlist = actRepository.find05(season, act);
			}else if(vehicle.equals(1) == true){//晴れで電車移動
				actlist = actRepository.find06(season, act);
			}else if(weather.equals(2) == true){//雨で車移動
				actlist = actRepository.find07(season, act);
			}else{//晴れで車移動
				actlist = actRepository.find08(season, act);
			}
		}
		Integer ran2 = actlist.size();
		Integer randomact  = random.nextInt(ran2);//乱数生成
		String actplan =actlist.get(randomact).getName();
		
		m.addAttribute("season", season);
		m.addAttribute("weather", weather);
		m.addAttribute("vehicle", vehicle);
		m.addAttribute("food", food);
		m.addAttribute("act", act);
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
		String name = users.getName();
		Integer sw = null;
		if(season.equals(1) == true) {
			if(weather.equals(1) == true) {
				sw = 1;
			}else {
				sw = 2;
			}
		}else if(season.equals(2) == true) {
			if(weather.equals(1) == true) {
				sw = 3;
			}else {
				sw = 4;
			}
		}else if(season.equals(3) == true) {
			if(weather.equals(1) == true) {
				sw = 5;
			}else {
				sw = 6;
			}
		}else if(season.equals(4) == true) {
			if(weather.equals(1) == true) {
				sw = 7;
			}else {
				sw = 8;
			}
		}
		
		m.addAttribute("sw", sw);
		m.addAttribute("name", name);
		m.addAttribute("vehicle", vehicle);
		m.addAttribute("eatplan", eatplan);
		m.addAttribute("actplan", actplan);
		
		return "result";
	}
	
	@PostMapping("/replan")
	public String replan() {
		return "form";
	}
			
}
