package com.yihao86.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yihao86.pojo.Users;
import com.yihao86.service.UsersService;

@Controller
public class UserController {

	@Autowired
	private UsersService us;

	@RequestMapping("/findUser")
	public String userLogin(Model mod, String phone, String cade, HttpSession session) {
		if ("18873299263".equals(phone) && "12345".equals(cade)) {
			System.out.println("登錄成功！~~~~~~~~");
			Users user = us.selectUser(phone);
			session.setAttribute("user", user);
			return "forward:/gogogo";
		}
		return "index";
	}

	@RequestMapping("/findUserById")
	public String searchUserById(int uid, Model mod, HttpSession session, String options) {
		String option = options == null ? "1" : options;
		Users user = (Users) session.getAttribute("user");
		Map<String, Object> umap = us.selectMyFolColl(uid);
		mod.addAttribute("umap", umap);
		List<Map<String, Object>> ulist = new ArrayList<>();
		if (option.equals("1")) {
			ulist = us.selectUserWatch(uid);
			for (Map<String, Object> map : ulist) {
				map.put("num", 0);
				map.put("crid", 0);
			}
		} else if (option.equals("2")) {
			ulist = us.selectCollVideos(uid);
			for (Map<String, Object> map : ulist) {
				map.put("crid", 0);
			}
		} else if (option.equals("3")) {
			ulist = us.selectUsersCourse(uid);
			for (Map<String, Object> map : ulist) {
				map.put("typename", null);
				map.put("v_difficulty", null);
			}
		} else if (option.equals("4")) {
			List<Map<String, Object>> tlist = us.selectUserTeachers(uid);
			Map<Integer, Object> map = us.selectTeacherVideo(uid);		
			mod.addAttribute("tlist", tlist);
			mod.addAttribute("map", map);
			mod.addAttribute("u", user);
			mod.addAttribute("option", option);
			//记得写关注脚本哦
			
			return "geren";
		}
		mod.addAttribute("ulist", ulist);
		mod.addAttribute("u", user);
		mod.addAttribute("option", option);
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