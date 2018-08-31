package com.yihao86.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yihao86.pojo.Teachers;
import com.yihao86.pojo.Videos;

public interface TeachersDao {
	
	List<Teachers> findAllTeacher();
	
	Teachers findOneTeacher(@Param("tid") int tid);
	
	//查询当前老师的所有视频
	List<Videos> teacherVideo(@Param("v_teacherId") int tid);
	
	//当前视频的观看人数
	int viewingNumber(@Param("h_vid") int vid);
	
	//查看当前老师的成就信息
	Map<String,Object> achievement(@Param("f_ftid") int tid);
	
	//查看当前老师的成就信息
	int total(@Param("h_tid") int tid);

}
