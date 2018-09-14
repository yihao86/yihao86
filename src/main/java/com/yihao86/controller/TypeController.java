package com.yihao86.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yihao86.pojo.Type;
import com.yihao86.pojo.Videos;
import com.yihao86.service.TypeService;
import com.yihao86.service.VideosService;

@Controller
public class TypeController {

	@Autowired
	private TypeService ts;

	@Autowired
	private VideosService vs;

	@RequestMapping("/findTyeps")
	public String queryTyeps(String f_trid, Model mod, String typeId,@RequestParam(name="pageNow",defaultValue="1") Integer pageNow) {		
		System.out.println("11111111111111");
		String ftrid = f_trid == null ? "1" : f_trid;
		List<Type> flist = ts.selectType();
		mod.addAttribute("flist", flist);
		System.out.println(flist.size());
		List<Type> slist = ts.selectSonList(Integer.valueOf(ftrid));
		mod.addAttribute("slist", slist);
		System.out.println(slist.size());
		PageHelper.startPage(pageNow, 12);
		PageInfo<Map<Object, String>> pageAll=null;
		List<Map<Object, String>> vlist=new ArrayList<>();
		if(typeId==null) {
			typeId="0";
			vlist= vs.selectVideoByOne(Integer.valueOf(ftrid));
			pageAll=new PageInfo<Map<Object, String>>(vlist);
			System.out.println(pageAll.getPages()+"\t"+pageAll.getPageNum()+"\t"+pageAll.getTotal());
			System.out.println(vlist.size());
			mod.addAttribute("vlist", vlist);
		}else {
			System.out.println("第二次进入。。。。。。。。。。。。。");
			vlist = vs.selectVideoByTid(Integer.valueOf(typeId));
			pageAll=new PageInfo<Map<Object, String>>(vlist);
			System.out.println(pageAll.getPages()+"\t"+pageAll.getPageNum()+"\t"+pageAll.getTotal());
			System.out.println(vlist.size());		
			mod.addAttribute("vlist", vlist);	
		}		
		mod.addAttribute("typeId", typeId);
		mod.addAttribute("ftrid", ftrid);
		mod.addAttribute("pages", pageAll.getPages());
		mod.addAttribute("pageNum", pageAll.getPageNum());
		mod.addAttribute("pageTotal",pageAll.getTotal());
		return "type";
	}

	
}
