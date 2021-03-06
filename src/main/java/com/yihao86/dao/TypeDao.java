package com.yihao86.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yihao86.pojo.Type;

public interface TypeDao {
 
	public List<Type> findType();
	
	public List<Type> findSonList(@Param("f_trid") int f_trid);
	
	List<Map<String,Object>> hotDoor();
}
