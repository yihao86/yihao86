package com.yihao86.service;

import java.util.List;
import java.util.Map;

import com.yihao86.pojo.History;
import com.yihao86.pojo.Videos;

public interface VideosService {
  
	public List<Map<String,Object>> searchAll();
	
	public Videos selectById(int vid);
	
	public List<Map<Object,String>> selectNewestVideo();
	
	public List<Map<Object,String>> selectVideoByTid(int typeId);
	
	public List<Map<Object,String>> selectVideoByOne(int f_trid);
	
	int insertHistory(History history);
}
