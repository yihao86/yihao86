package com.yihao86.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yihao86.pojo.Teachers;
import com.yihao86.pojo.Type;
import com.yihao86.pojo.Videos;
import com.yihao86.service.TeachersService;


@Controller
public class TeachersController {

	@Autowired
	private TeachersService ts;
	
	@RequestMapping("clickAllTeacher")
	public String allTeachers(Model model,String tid,String t_occupation) {
		List<Map<String,Object>> map = ts.findAllAchievement(Integer.valueOf(t_occupation));
		List<Type> typelist = ts.teacherType();
		model.addAttribute("map", map);
		model.addAttribute("typelist", typelist);
		model.addAttribute("t_occupation", t_occupation);
		return "allteachers";
	}
	
	@RequestMapping("clickOneTeacher")
	public String oneTeacher(Model model,String tid) {
		Teachers teacher = ts.findOneTeacher(Integer.valueOf(tid));
		List<Videos> vlist = ts.teacherVideo(Integer.valueOf(tid));
		//查看当前视频的学习总人数
		List<Integer> list = ts.viewingNumber(Integer.valueOf(tid));
		//查看当前老师的成就信息
		Map<String,Object> achievement = ts.achievement(Integer.valueOf(tid));
		//查看当前老师的学习总次数
		int total = ts.total(Integer.valueOf(tid));
		
		model.addAttribute("teacher", teacher);
		model.addAttribute("vlist", vlist);
		model.addAttribute("list", list);
		model.addAttribute("achievement", achievement);
		model.addAttribute("total", total);
		return "teacher";
	}
	
}
