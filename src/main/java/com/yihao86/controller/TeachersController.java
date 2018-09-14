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
import com.github.pagehelper.Page;
import com.yihao86.pojo.Teachers;
import com.yihao86.pojo.Type;
import com.yihao86.pojo.Videos;
import com.yihao86.service.TeachersService;


@Controller
public class TeachersController {

	@Autowired
	private TeachersService ts;
	
	@RequestMapping("clickAllTeacher")
	public String allTeachers(Model model,String tid,String t_occupation,@RequestParam(name="pageNow",defaultValue="1") Integer pageNow) {
		
		List<Map<String,Object>> map = ts.findAllAchievement(Integer.valueOf(t_occupation),pageNow);
		Page<Teachers> pageAll=null;
		List<Type> typelist = ts.teacherType();
		for (Map<String, Object> maps : map) {
			pageAll=(Page<Teachers>)maps.get("pageAll");
			
		}
		if(pageAll!=null){
			model.addAttribute("pages", pageAll.getPages());
			model.addAttribute("pageNum", pageAll.getPageNum());
			model.addAttribute("pageTotal",pageAll.getTotal());
		}
		model.addAttribute("map", map);
		model.addAttribute("typelist", typelist);
		model.addAttribute("t_occupation", t_occupation);
		
		return "allteachers";
	}
	
	@RequestMapping("clickOneTeacher")
	public String oneTeacher(Model model,String tid,@RequestParam(name="pageNow",defaultValue="1") Integer pageNow) {
		Teachers teacher = ts.findOneTeacher(Integer.valueOf(tid));
		PageHelper.startPage(pageNow,6);
		
		List<Videos> vlist = ts.teacherVideo(Integer.valueOf(tid));
		PageInfo<Videos> pageAll=new PageInfo<Videos>(vlist);
		System.out.println(pageAll.getPages()+"\t"+pageAll.getPageNum()+"\t"+pageAll.getTotal());
		//查看当前视频的学习总人数
		List<Integer> list = ts.viewingNumber(Integer.valueOf(tid));
		//查看当前老师的成就信息
		Map<String,Object> achievement = ts.achievement(Integer.valueOf(tid));
		//查看当前老师的学习总次数
		int total = ts.total(Integer.valueOf(tid));
		
		model.addAttribute("teacher", teacher);
		model.addAttribute("vlist", pageAll.getList());
		model.addAttribute("list", list);
		model.addAttribute("achievement", achievement);
		model.addAttribute("total", total);
		model.addAttribute("pages", pageAll.getPages());
		model.addAttribute("pageNum", pageAll.getPageNum());
		model.addAttribute("pageTotal",pageAll.getTotal());
		return "teacher";
	}
	
}
