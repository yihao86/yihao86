package com.yihao86.service;

import java.util.List;

import com.yihao86.pojo.Type;

public interface TypeService {

	 public List<Type> selectType();
	 
	 public List<Type> selectSonList(int f_trid);
	 
}
