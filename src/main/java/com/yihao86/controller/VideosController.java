package com.yihao86.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yihao86.pojo.Videos;
import com.yihao86.service.VideosService;

@Controller
public class VideosController {

	@Autowired
	private VideosService vs;

	@RequestMapping("gogogo")
	public String queryVideos(Model mod) {
		System.out.println("查询所有视频方法.......");
		List<Videos> vlist = vs.searchAll();
		System.out.println(vlist.size());
		mod.addAttribute("vlist", vlist);
		return "index";
	}

}
