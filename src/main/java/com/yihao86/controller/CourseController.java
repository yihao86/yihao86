package com.yihao86.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yihao86.pojo.Course;
import com.yihao86.pojo.Type;
import com.yihao86.pojo.Videos;
import com.yihao86.service.AlbumService;
import com.yihao86.service.CourseService;
import com.yihao86.service.TeachersService;
import com.yihao86.service.TypeService;

@Controller
public class CourseController {
	
	@Autowired
	private TypeService ts;
	
	@Autowired
	private AlbumService as;
	
	@Autowired
	private CourseService cs;
	
	@RequestMapping("/fandCourse")
	public String fandCourse(Model model) {
		List<Type> tlist = ts.selectType();
		model.addAttribute("tlist", tlist);
		List<Course> clist = as.fandAlbum(0);
		model.addAttribute("clist", clist);
		return "teacherCourse";
	}
	
	@RequestMapping("/fandOneCourse")
	public String fandOneCourse(Model model,String crid) {
		List<Videos> vlist = cs.fandOneCourse(Integer.valueOf(crid));
		model.addAttribute("vlist", vlist);
		List<String> list = cs.fandChapter(Integer.valueOf(crid));
		model.addAttribute("list",list);
		Map<String,Object> v = cs.fandOneChapter(Integer.valueOf(crid));
		model.addAttribute("v", v);
		Map<String,Object> courseInfo = cs.fandCourseInfo(Integer.valueOf(crid));
		model.addAttribute("courseInfo", courseInfo);
		return "course";
	}
}
