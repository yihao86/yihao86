package com.yihao86.service;

import java.util.List;
import java.util.Map;

import com.yihao86.pojo.Course;
import com.yihao86.pojo.Videos;

public interface CourseService {

	List<Videos> fandOneCourse(int crid);
	
	List<String> fandChapter(int crid);
	
	//查询章节下面的课程
	Map<String,Object>  fandOneChapter(int crid);
	
	//查看课程的基本信息
	Map<String,Object> fandCourseInfo(int crid);
	
	//查询专辑下面的课程
	List<Map<String,Object>> fandAlbumCourse(int a_typeId);
	
	//查找购买数量
	List<Integer> fandNumber(int a_typeId);
	
	//查找单个课程的信息
	List<Integer> findCourseInfo();	
}
