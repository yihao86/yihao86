package com.yihao86.service;

import java.util.List;

import com.yihao86.pojo.Album;
import com.yihao86.pojo.Course;

public interface AlbumService {

	//查询所有的专辑
	List<Album> fandAllAlbum(int typeid,int aid);
	
	//查询单个专辑下面的课程
	List<Course> fandAlbum(int aid);
}
