package com.yihao86.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yihao86.pojo.History;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yihao86.pojo.Teachers;
import com.yihao86.pojo.Users;
import com.yihao86.pojo.Videos;
import com.yihao86.service.TeachersService;
import com.yihao86.service.CourseService;
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
	public String queryVideos(Model mod, int vid , History history,HttpSession session) {
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
		
		Users user = (Users) session.getAttribute("user");
		history.setH_vid(vid);
		history.setH_uid(user.getUid());
		history.setH_crid(v.getV_crid());
		history.setH_tid(v.getV_teacherId());
		vs.insertHistory(history);
		
		return "video";
	}

	public String queryVideosByTid(int f_trid) {
           return "type";
	}

	
	@RequestMapping("findexe")
	public String findexe(Model mod,@RequestParam(name="pageNow",defaultValue="1") Integer pageNow) {
		PageHelper.startPage(pageNow,12);
		List<Map<String, Object>> list=vs.findByWorks();
		PageInfo<Map<String, Object>> pageAll=new PageInfo<Map<String, Object>>(list);
		Map<String,Object> dw = list.get(0);
		//查看当前老师的成就信息
		Map<String,Object> achievement = ts.achievement((int)dw.get("tid"));
		mod.addAttribute("achievement",achievement);
		mod.addAttribute("data",dw);
		mod.addAttribute("list", list);
		mod.addAttribute("pages", pageAll.getPages());
		mod.addAttribute("pageNum", pageAll.getPageNum());
		mod.addAttribute("pageTotal",pageAll.getTotal());
		return "exe";
	}
}
