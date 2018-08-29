package com.yihao86.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yihao86.pojo.Teachers;

public interface TeachersService {

	List<Teachers> findAllTeacher();
	
	Teachers findOneTeacher(int tid);
}
