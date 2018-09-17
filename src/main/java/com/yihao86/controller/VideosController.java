package com.yihao86.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yihao86.pojo.Teachers;
import com.yihao86.pojo.Videos;
import com.yihao86.service.CourseService;
import com.yihao86.service.TeachersService;
import com.yihao86.service.VideosService;

@Controller
public class VideosController {

	@Autowired
	private VideosService vs;
	
	@Autowired
	private CourseService cs;
	
	@Autowired
	private TeachersService ts;

	@RequestMapping("goVideo")
	public String queryVideos(Model mod, int vid) {
		Videos v = vs.selectById(vid);
		
		mod.addAttribute("v", v);
		List<Videos> vlist = cs.fandOneCourse(v.getV_crid());
		mod.addAttribute("vlist", vlist);
		List<String> list = cs.fandChapter(v.getV_crid());
		mod.addAttribute("list",list);
		Map<String,Object> vmap = cs.fandOneChapter(v.getV_crid());
		mod.addAttribute("vmap", vmap);
		Map<String,Object> courseInfo = cs.fandCourseInfo(v.getV_crid());
		mod.addAttribute("courseInfo", courseInfo);
		
		Teachers teacher = ts.findOneTeacher(v.getV_teacherId());
		Map<String,Object> achievement = ts.achievement(v.getV_teacherId());
		mod.addAttribute("teacher", teacher);
		mod.addAttribute("achievement", achievement);
		
		return "video";
	}

	public String queryVideosByTid(int f_trid) {
           return "type";
	}

}
