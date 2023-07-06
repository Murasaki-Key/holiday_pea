package com.example.demo.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.TimeTable;
import com.example.demo.entity.TimeTableList;
import com.example.demo.model.User;
import com.example.demo.repository.TimeTableListRepository;
import com.example.demo.repository.TimeTableRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class TimeTableController {
	@Autowired
	HttpSession session;
	
	@Autowired
	User users;
	
	@Autowired
	TimeTableListRepository timetablelistRepository;
	
	@Autowired
	TimeTableRepository timetableRepository;
	
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
		
		//SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		
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
	
	@PostMapping("/detail/confirm")
	public String confirm(Model m) {
	//作成した予定
	List <TimeTableList> timetables = timetablelistRepository.findAll1();
	m.addAttribute("timetables",timetables);
	
	//日付
	Calendar cl = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String plandate = sdf.format(cl.getTime());
    m.addAttribute("plandate",plandate);
    
    //ユーザ名
    String username = users.getName();
    m.addAttribute("username",username);
		
		return "timetableconfirm";
	}
	
	@PostMapping("/detail/replan")
	public String replan(Model m) {
		//作成した予定
		List <TimeTableList> timetables = timetablelistRepository.findAll1();
		m.addAttribute("timetables",timetables);
		
		return "timetable";
	}
	
	@PostMapping("/detail/complete")
	public String complete(
			@RequestParam(name="username",defaultValue="") String username ,
			@RequestParam(name="plandate",defaultValue="") String plandate ,
			Model m
			) {
		//作成した予定の取得
		List <TimeTableList> timetables = timetablelistRepository.findAll1();
		//planidの決定
		Integer planid  = 1;
		List<TimeTable> plantable = timetableRepository.decidePlanid(username);
		if(plantable.isEmpty()) {
			planid = 1;
		}else {
			planid = plantable.size() + 1;
		}
		
		
		Integer i = 0;
		while(i < timetables.size()) {
			List <TimeTableList> tt = timetablelistRepository.findRow(i);
			String starttime = tt.get(0).getStarttime();
			String finishtime = tt.get(0).getFinishtime();
			String action = tt.get(0).getAction();
			String place = tt.get(0).getPlace();
			TimeTable timetable = new TimeTable(username,planid,starttime,finishtime,action,place,plandate);
			timetableRepository.save(timetable);
			i++;
		}
		
		timetablelistRepository.deleteAll();
		
		return "timetablecomplete";
	}
			
}
