package com.yihao86.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yihao86.service.WorksService;

@Controller
public class WorksController {

	
	@Autowired
	private WorksService ws;
	
	@RequestMapping("findexe")
	public String findexe(Model mod) {
		List<Map<String, Object>> wlist = ws.SelectOnNew();
		
	    mod.addAttribute("newlist", wlist);

		return "exe";
	}
}
