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
	public String fandAllAlbum(Model model,String typeid,@RequestParam(name="pageNow",defaultValue="1") Integer pageNow) {
		List<Type> tlist = ts.selectType();
		PageHelper.startPage(pageNow,12);
		List<Album> alist = as.fandAllAlbum(Integer.valueOf(typeid),0);
		PageInfo<Album> pageAll=new PageInfo<Album>(alist);
		System.out.println(pageAll.getPages()+"\t"+pageAll.getPageNum()+"\t"+pageAll.getTotal());
		model.addAttribute("tlist", tlist);
		model.addAttribute("alist", alist);
		model.addAttribute("typeid", typeid);
		model.addAttribute("pages", pageAll.getPages());
		model.addAttribute("pageNum", pageAll.getPageNum());
		model.addAttribute("pageTotal",pageAll.getTotal());
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
