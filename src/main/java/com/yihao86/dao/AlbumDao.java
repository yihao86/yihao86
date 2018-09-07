package com.yihao86.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yihao86.pojo.Album;
import com.yihao86.pojo.Course;

public interface AlbumDao {
	
	//查询所有的专辑
	List<Album> fandAllAlbum(@Param("a_typeId") int typeid,@Param("aid") int aid);
	
	//查询单个专辑下面的课程
	List<Course> fandAlbum(@Param("cr_aid") int aid);

}
