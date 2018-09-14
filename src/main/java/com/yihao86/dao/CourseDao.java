package com.yihao86.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yihao86.pojo.Course;
import com.yihao86.pojo.Videos;

public interface CourseDao {

	List<Videos> fandOneCourse(@Param("v_crid") int crid);
	
	//查询章节下面的课程
	List<Videos>fandChapter(@Param("title") String title);
	
	//查询课程的基本信息
	Map<String,Object> fandCourseInfo(@Param("crid") int crid);
	
	//查询专辑下面的课程
	List<Map<String,Object>> fandAlbumCourse(@Param("a_typeId") int a_typeId);
	
	//查找购买数量
	int fandNumber(@Param("p_cid") int crid);
	
	List<Course> findCourse();
	
	int findCourseNum(@Param("v_crid") int v_crid );
}
