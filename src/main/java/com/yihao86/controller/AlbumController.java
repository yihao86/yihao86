package com.yihao86.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yihao86.pojo.Album;
import com.yihao86.pojo.Course;
import com.yihao86.pojo.Teachers;
import com.yihao86.pojo.Type;
import com.yihao86.service.AlbumService;
import com.yihao86.service.TeachersService;
import com.yihao86.service.TypeService;

@Controller
public class AlbumController {
	
	@Autowired
	private AlbumService as;
	
	@Autowired
	private TypeService ts;
	
	@Autowired
	private TeachersService tcs;
	
	@RequestMapping("/fandAllAlbum")
	public String fandAllAlbum(Model model,String typeid) {
		List<Type> tlist = ts.selectType();
		List<Album> alist = as.fandAllAlbum(Integer.valueOf(typeid),0);
		model.addAttribute("tlist", tlist);
		model.addAttribute("alist", alist);
		model.addAttribute("typeid", typeid);
		return "allalbum";
	}
	
	
	@RequestMapping("/fandAlbum")
	public String fandAlbum(Model model,String aid,String tcid) {
		List<Course> clist = as.fandAlbum(Integer.valueOf(aid));
		//查看老师
		Teachers teacher = tcs.findOneTeacher(Integer.valueOf(tcid));
		List<Album> a = as.fandAllAlbum(0,Integer.valueOf(aid));
		Map<String,Object> achievement = tcs.achievement(Integer.valueOf(tcid));
		model.addAttribute("clist", clist);
		model.addAttribute("teacher", teacher);
		model.addAttribute("album", a);
		model.addAttribute("achievement",achievement);
		return "album";
	}

}
