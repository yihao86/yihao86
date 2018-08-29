package com.yihao86.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yihao86.pojo.Teachers;
import com.yihao86.service.TeachersService;

@Controller
public class TeachersController {

	@Autowired
	private TeachersService ts;
	
	@RequestMapping("clickAllTeacher")
	public String allTeachers(Model model) {
		List<Teachers> tlist = ts.findAllTeacher();
		model.addAttribute("tlist", tlist);
		return "allteachers";
	}
	
	@RequestMapping("clickOneTeacher")
	public String oneTeacher(Model model,String tid) {
		Teachers teacher = ts.findOneTeacher(Integer.valueOf(tid));
		model.addAttribute("teacher", teacher);
		return "teacher";
	}
}
