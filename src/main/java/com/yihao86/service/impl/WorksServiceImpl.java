package com.yihao86.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yihao86.dao.WorksDao;
import com.yihao86.service.WorksService;

@Service
public class WorksServiceImpl implements WorksService {

	@Autowired
	private WorksDao wd;

	@Override
	public List<Map<String, Object>> SelectOnNew() {
		return wd.fandOnNew();
	}

	@Override
	public List<Map<String, Object>> SelectAllById(int w_tid) {
			List<Map<String, Object>> tids = SelectOnNew();
			for (Map<String, Object> map : tids) {
				
			}	
			return tids;
	}
	


}
