package com.yihao86.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.yihao86.dao.VideosDao;
import com.yihao86.pojo.Videos;
import com.yihao86.service.VideosService;

@Service
@Transactional
@AutoConfigureAfter({VideosDao.class})
public class VideosServiceImpl  implements VideosService{
	
	@Autowired
	private VideosDao vdao;

	@Override
	public List<Videos> searchAll() {
	
		return vdao.findAll();
	}


}
