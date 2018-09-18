package com.yihao86.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihao86.dao.VideosDao;
import com.yihao86.pojo.History;
import com.yihao86.pojo.Videos;
import com.yihao86.service.VideosService;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;

@Service
@Transactional
@AutoConfigureAfter({VideosDao.class})
public class VideosServiceImpl  implements VideosService{
	
	private static final List<Videos> Page = null;
	@Autowired
	private VideosDao vdao;
	
	/**
	 * 主页查询所有
	 */
	@Override
	public List<Map<String,Object>> searchAll() {
		/*
		List<Map<String,Object>> list = vdao.findAll();
		List<String> s = new ArrayList<String>();
		for (Map<String,Object> map : list) {
			String path = "C:\\tomcat_media\\webapps\\video\\"+map.get("v_vpath");
			File file = new File(path);
			System.out.println(path);
			String time = ReadVideoTime(file);
			s.add(time);
		}
		Map<String,Object> m = new HashMap<>();
		m.put("time", s);
		list.add(m);
		return list;*/
		return vdao.findAll();
	}
	
	
	
	public static String ReadVideoTime(File source) {
		Encoder encoder = new Encoder();
		String length = "";
		try {
			System.out.println(source);
		MultimediaInfo m = encoder.getInfo(source);
		long ls = m.getDuration()/1000;
		int hour = (int) (ls/3600);
		int minute = (int) (ls%3600)/60;
		int second = (int) (ls-hour*3600-minute*60);
		String min = "";
		String sec = "";
		if(minute < 10) {
			min = "0"+minute;
		}
		if(second < 10)
			sec = "0"+second;
		length = hour+"时"+min+"分"+sec+"秒";
		} catch (Exception e) {
		e.printStackTrace();
		}
		return length;
		}
	

	 /**
	  * 根据视频id查询并播放
	  */
	@Override
	public Videos selectById(int vid) {
		
		return vdao.findVideoById(vid);
	}
	
	/**
	 * 查询本天的7天前上传的所有视频
	 * @return
	 */
	@Override
	public List<Map<Object,String>> selectNewestVideo() {
		
		return vdao.findNewestVideo();
	}

	 /**
	  * 根据子分类ID查询所属它的所有视频
	  */
	@Override
	public List<Map<Object,String>> selectVideoByTid(int typeId) {
		
		List<Map<Object,String>> list=vdao.findVideosByTid(typeId);
	
		return list;
	}
    
	/**
	 * 查询所有的时候默认选择Java
	 */
	@Override
	public List<Map<Object,String>> selectVideoByOne(int f_trid) {
		
		return vdao.findVideosByOne(f_trid);
	}



	@Override
	public int insertHistory(History history) {
		
		return vdao.insertHistory(history);
	}

	






}
