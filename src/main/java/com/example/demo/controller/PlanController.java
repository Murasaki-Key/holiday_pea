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
		List <Eat> eatlist = eatRepository.findBySeasonid(season);//季節で絞り込む
		
		if(food.equals(5)) {
			
		}else {
			
		if(food.equals(1)) {
			
		}
		if(food.equals(2)) {
			
		}
		if(food.equals(3)) {
			
		}
		if(food.equals(4)) {
			
		}
		}
		
		//乱数生成
		Integer randomfood  = random.nextInt(19) + 1;
			
	
		
		//行動をランダムで決定する
		List <Act> actlist = actRepository.findBySeasonid(season);//季節で絞り込む
		
		if(act.equals(5)){
			
		}else {
			
		if(act.equals(1)) {
			
		}
		if(act.equals(2)) {
			
		}
		if(act.equals(3)) {
			
		}
		if(act.equals(4)) {
			
		}
		}
		
		//乱数生成
		Integer randomact  = random.nextInt(19) + 1;
		
		return "";
	}
	

}
