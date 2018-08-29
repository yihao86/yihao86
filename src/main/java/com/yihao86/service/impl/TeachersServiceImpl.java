package com.yihao86.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yihao86.dao.TeachersDao;
import com.yihao86.pojo.Teachers;
import com.yihao86.service.TeachersService;

@Service
public class TeachersServiceImpl implements TeachersService {
	
	@Autowired
	private TeachersDao tdao;

	@Override
	public List<Teachers> findAllTeacher() {
		return tdao.findAllTeacher();
	}

	@Override
	public Teachers findOneTeacher(int tid) {
		return tdao.findOneTeacher(tid);
	}

}
