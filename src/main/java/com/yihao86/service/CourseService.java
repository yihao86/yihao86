package com.yihao86.service;

import java.util.List;
import java.util.Map;

import com.yihao86.pojo.Videos;

public interface CourseService {

	List<Videos> fandOneCourse(int crid);
	
	List<String> fandChapter(int crid);
	
	//查询章节下面的课程
	Map<String,Object>  fandOneChapter(int crid);
}
