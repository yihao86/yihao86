package com.yihao86.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface WorksDao {
	
	public List<Map<String, Object>> fandOnNew();

	public List<Map<String, Object>> fandAllById(@Param("w_tid") int w_tid);
}
