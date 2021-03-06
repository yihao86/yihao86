package com.yihao86.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
	private CourseService cs;
	
	@RequestMapping("/fandCourse")
	public String fandCourse(Model model,String a_typeId,@RequestParam(name="pageNow",defaultValue="1") Integer pageNow) {
		List<Type> tlist = ts.selectType();
		model.addAttribute("tlist", tlist);
		PageHelper.startPage(pageNow,6);
		List<Map<String,Object>> clist = cs.fandAlbumCourse(Integer.valueOf(a_typeId));
		PageInfo<Map<String,Object>> pageAll=new PageInfo<Map<String,Object>>(clist);
		System.out.println(pageAll.getPages()+"\t"+pageAll.getPageNum()+"\t"+pageAll.getTotal());
		
		model.addAttribute("clist", clist);
		model.addAttribute("a_typeId", a_typeId);
		model.addAttribute("pages", pageAll.getPages());
		model.addAttribute("pageNum", pageAll.getPageNum());
		model.addAttribute("pageTotal",pageAll.getTotal());
		List<Integer> list = cs.fandNumber(Integer.valueOf(a_typeId));
		model.addAttribute("list", list);
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
