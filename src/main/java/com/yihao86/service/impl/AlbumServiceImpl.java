package com.yihao86.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yihao86.dao.AlbumDao;
import com.yihao86.pojo.Album;
import com.yihao86.pojo.Course;
import com.yihao86.service.AlbumService;

@Service
public class AlbumServiceImpl implements AlbumService {
	
	@Autowired
	private AlbumDao adao;

	@Override
	public List<Album> fandAllAlbum(int typeid,int aid) {
		return adao.fandAllAlbum(typeid,aid);
	}

	@Override
	public List<Course> fandAlbum(int aid) {
		return adao.fandAlbum(aid);
	}
	
}
