package com.yihao86.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yihao86.dao.TeachersDao;
import com.yihao86.dao.TypeDao;
import com.yihao86.dao.VideosDao;
import com.yihao86.pojo.Videos;
import com.yihao86.service.VideosService;

@Service
@Transactional
@AutoConfigureAfter({VideosDao.class})
public class VideosServiceImpl  implements VideosService{
	
	private static final List<Videos> Page = null;
	@Autowired
	private VideosDao vdao;
	
	@Autowired
	private TeachersDao td;
	/**
	 * 主页查询所有
	 */
	@Override
	public List<Map<Object,String>> searchAll() {
	
		return vdao.findAll();
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
	public List<Map<String, Object>> findByWorks() {
		// TODO Auto-generated method stub
		return vdao.findByWorks();
	}



}
