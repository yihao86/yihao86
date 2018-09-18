package com.yihao86.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yihao86.pojo.Course;
import com.yihao86.pojo.Type;
//import com.yihao86.quartz.ScheduledTasks;
import com.yihao86.service.CourseService;
import com.yihao86.service.TeachersService;
import com.yihao86.service.TypeService;
import com.yihao86.service.VideosService;

@Controller
public class IndexController {
	
	@Autowired
	private VideosService vs;
	
	@Autowired
	private TypeService ts;
	
	@Autowired
	private TeachersService tcs;
	
	/*@Autowired
	private ScheduledTasks st;*/
	
	@Autowired
	private CourseService cs;

	
	@RequestMapping("gogogo")
	public String index(Model mod) {
		System.out.println("hhhhhh_主页查询方法");
		List<Map<String,Object>> vlist = vs.searchAll();		
		List<Type> tlist=ts.selectType();
		List<Map<Object,String>> newlist=vs.selectNewestVideo();
		mod.addAttribute("tlist", tlist);
		mod.addAttribute("vlist", vlist);
	    mod.addAttribute("newlist", newlist);
	   	List<Map<String,Object>> map=tcs.findAllAchievement(0,-1);
		System.out.println(map.size());
		mod.addAttribute("map",map);
		
//		List<Map<String,Object>> vmap = st.reportCurrentByCron();
		
		//mod.addAttribute("vmap", vmap);
		
		List<Map<String,Object>> clist = cs.fandAlbumCourse(0);
		mod.addAttribute("clist", clist);
		
		List<Integer> list = cs.fandNumber(0);
		mod.addAttribute("list", list);
		
		List<Integer> courseNum = cs.findCourseInfo();
		mod.addAttribute("courseNum",courseNum);
		
		List<Map<String,Object>> hot = ts.hotDoor();
		mod.addAttribute("hot",hot);
		return "index";
	}

}
