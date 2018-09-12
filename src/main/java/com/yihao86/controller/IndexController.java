package com.yihao86.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yihao86.pojo.Type;
import com.yihao86.pojo.Videos;
import com.yihao86.quartz.ScheduledTasks;
import com.yihao86.service.TypeService;
import com.yihao86.service.VideosService;

@Controller
public class IndexController {
	
	@Autowired
	private VideosService vs;
	
	@Autowired
	private TypeService ts;
	
	@Autowired
	private ScheduledTasks st;

	@RequestMapping("gogogo")
	public String index(Model mod) {
		System.out.println("hhhhhh_主页查询方法");
		List<Map<Object,String>> vlist = vs.searchAll();		
		List<Type> tlist=ts.selectType();
		List<Map<Object,String>> newlist=vs.selectNewestVideo();
		String time = st.reportCurrentByCron();
		mod.addAttribute("tlist", tlist);
		mod.addAttribute("vlist", vlist);
	    mod.addAttribute("newlist", newlist);
	    mod.addAttribute("time", time);
		return "index";
	}

}
