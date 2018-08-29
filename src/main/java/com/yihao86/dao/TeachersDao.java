package com.yihao86.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yihao86.pojo.Teachers;

public interface TeachersDao {
	
	List<Teachers> findAllTeacher();
	
	Teachers findOneTeacher(@Param("tid") int tid);

}
