package com.yihao86.service;

import java.util.List;
import java.util.Map;

public interface WorksService {
	
	/**
	 * 查询最新发布每周一练信息
	 * @param w_tid
	 * @return
	 */
	public List<Map<String, Object>> SelectOnNew();
	

	/**
	 * 查询该老师发布每周一练所有作品信息
	 * @param w_tid
	 * @return
	 */
	public List<Map<String, Object>> SelectAllById(int w_tid);
}
