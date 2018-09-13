package com.yihao86.service;

import java.util.List;
import java.util.Map;

import com.yihao86.pojo.Teachers;
import com.yihao86.pojo.Type;
import com.yihao86.pojo.Videos;

public interface TeachersService {

	List<Teachers> findAllTeacher(int typeid);
	
	Teachers findOneTeacher(int tid);
	
	//查询当前老师的所有视频
	List<Videos> teacherVideo(int tid);
	
	//查询当前视频的观看人数
	List<Integer> viewingNumber(int tid);
	
	//查看当前老师的成就信息
	Map<String,Object> achievement(int tid);
		
	//查看当前老师的学习总次数
	int total(int tid);
	
	
	List<Map<String,Object>> findAllAchievement(int t_occupation,int pageNow);
	
	//查看老师页面的职业
	List<Type> teacherType();
	
	//查询所有老师的成就
	//List<Map<String, Object>> findTeacherAchievement(int tid);
	
}
