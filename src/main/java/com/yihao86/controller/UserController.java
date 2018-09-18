package com.yihao86.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yihao86.pojo.Users;
import com.yihao86.pojo.Videos;
import com.yihao86.service.UsersService;
import com.yihao86.util.SendMsg;

@Controller
public class UserController {

	@Autowired
	private UsersService us;
	
	@Autowired
	@Qualifier("redisTemplate")
	private RedisTemplate rdt;
	

	@RequestMapping("/findUser")
	public String userLogin(Model mod, String phone, String cade, HttpSession session) {
			String e = us.selectUser(phone,cade,session);
			if(e != null) {
				return "forward:/errorMsg";
			}
		return "forward:/gogogo";
	}
	
	@RequestMapping("/errorMsg")
	@ResponseBody
	public String errorMsg(Model mod) {
		return "error";
	}
	
	@RequestMapping("/findMsg")
	@ResponseBody
	public void send(String phone) {
		//String msg = SendMsg.Send(phone);
		//rdt.opsForValue().set("msg"+phone, msg);
		//rdt.expire("msg"+phone, 60*5,TimeUnit.SECONDS);
		
	}

	@RequestMapping("/findUserById")
	public String searchUserById(int uid, Model mod, HttpSession session, String options,@RequestParam(name="pageNow",defaultValue="1") Integer pageNow) {
		String option = options == null ? "1" : options;
		Users user = (Users) session.getAttribute("user");
		Map<String, Object> umap = us.selectMyFolColl(uid);
		mod.addAttribute("umap", umap);
		PageHelper.startPage(pageNow, 6);
		List<Map<String, Object>> ulist = new ArrayList<>();
		PageInfo<Map<String, Object>> pageAll=null;
		if (option.equals("1")) {
			ulist = us.selectUserWatch(uid);
			pageAll=new PageInfo<Map<String, Object>>(ulist);
			for (Map<String, Object> map : ulist) {
				map.put("num", 0);
				map.put("crid", 0);
			}
		} else if (option.equals("2")) {
			ulist = us.selectCollVideos(uid);
			pageAll=new PageInfo<Map<String, Object>>(ulist);
			for (Map<String, Object> map : ulist) {
				map.put("crid", 0);
			}
		} else if (option.equals("3")) {
			ulist = us.selectUsersCourse(uid);
			pageAll=new PageInfo<Map<String, Object>>(ulist);
			for (Map<String, Object> map : ulist) {
				map.put("typename", null);
				map.put("v_difficulty", null);
			}
		} else if (option.equals("4")) {
			List<Map<String, Object>> tlist = us.selectUserTeachers(uid);
			PageInfo<Map<String, Object>> pageAllt=new PageInfo<Map<String, Object>>(tlist);
			System.out.println(pageAllt.getPages()+"\t"+pageAllt.getPageNum()+"\t"+pageAllt.getTotal());
			Map<Integer, Object> map = us.selectTeacherVideo(uid);		
			mod.addAttribute("tlist", tlist);
			mod.addAttribute("map", map);
			mod.addAttribute("pages", pageAllt.getPages());
			mod.addAttribute("pageNum", pageAllt.getPageNum());
			mod.addAttribute("pageTotal",pageAllt.getTotal());
			mod.addAttribute("u", user);
			mod.addAttribute("option", option);
			//记得写关注脚本哦
			
			return "geren";
		}
		mod.addAttribute("ulist", ulist);
		mod.addAttribute("u", user);
		mod.addAttribute("option", option);
		mod.addAttribute("pages", pageAll.getPages());
		mod.addAttribute("pageNum", pageAll.getPageNum());
		mod.addAttribute("pageTotal",pageAll.getTotal());
		return "geren";
	}
  
   @RequestMapping("editTeacherById")
   public void editTeacherById(String tid,HttpSession session,HttpServletResponse response)
		   throws IOException {
		Users user = (Users) session.getAttribute("user");
	   // us.deleteTeachers(user.getUid(),Integer.valueOf(tid));
	    OutputStream out=response.getOutputStream();	    
	    out.write("Y".getBytes());
	  
   }
}
