package com.yihao86.controller;

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

	@RequestMapping("goVideo")
	public String queryVideos(Model mod, int vid) {
		System.out.println("根据ID查询视频方法.......");
		Videos v = vs.selectById(vid);
		mod.addAttribute("v", v);
		return "video";
	}

	public String queryVideosByTid(int f_trid) {
		
		
           return "type";
	}

}
