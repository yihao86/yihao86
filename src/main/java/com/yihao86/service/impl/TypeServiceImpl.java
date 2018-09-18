package com.yihao86.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihao86.dao.TypeDao;
import com.yihao86.dao.VideosDao;
import com.yihao86.pojo.Type;
import com.yihao86.service.TypeService;

@Service
@Transactional
@AutoConfigureAfter({TypeDao.class})
public class TypeServiceImpl implements TypeService {

	
	@Autowired
	private TypeDao tdao;
	
	@Override
	public List<Type> selectType() {
		// TODO Auto-generated method stub
		return tdao.findType();
	}

	@Override
	public List<Type> selectSonList(int f_trid) {
		
		
		
		return tdao.findSonList(f_trid);
	}

	@Override
	public List<Map<String, Object>> hotDoor() {
		return tdao.hotDoor();
	}



	

}
