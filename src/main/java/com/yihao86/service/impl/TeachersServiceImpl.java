package com.yihao86.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yihao86.dao.TeachersDao;
import com.yihao86.pojo.Teachers;
import com.yihao86.pojo.Type;
import com.yihao86.pojo.Videos;
import com.yihao86.service.TeachersService;

@Service
public class TeachersServiceImpl implements TeachersService {
	
	@Autowired
	private TeachersDao tdao;

	@Override
	public List<Teachers> findAllTeacher(int typeid) {
		return tdao.findAllTeacher(typeid);
	}
	
	@Override
	public List<Map<String,Object>> findAllAchievement(int t_occupation,int pageNow){
		List<Teachers> tlist = tdao.findAllTeacher(t_occupation);
		List<Map<String,Object>> list = new ArrayList<>();
		for (Teachers t : tlist) {
			Map<String,Object> map = new HashMap<>();
			List<Videos> vlist = tdao.teacherVideo(t.getTid());
			String typename = tdao.oneType(t.getTid());
			map = tdao.achievement(t.getTid());
			map.put("vlist", vlist);
			map.put("Teachers", t);
			map.put("typename", typename);
			list.add(map);
		}
		return list;
	} 

	@Override
	public Teachers findOneTeacher(int tid) {
		return tdao.findOneTeacher(tid);
	}

	@Override
	public List<Videos> teacherVideo(int tid) {
		return tdao.teacherVideo(tid);
	}

	@Override
	public List<Integer> viewingNumber(int tid) {
		List<Videos> vlist = tdao.teacherVideo(tid);
		List<Integer> list = new ArrayList<>();
		for (Videos v : vlist) {
			int i = tdao.viewingNumber(v.getVid());
			list.add(i);
		}
		return list;
	}
	
	@Override
	public int total(int tid) {
		return tdao.total(tid);
	}

	@Override
	public Map<String, Object> achievement(int tid) {
		return tdao.achievement(tid);
	}

	@Override
	public List<Type> teacherType() {
		return tdao.teacherType();
	}

	/*@Override
	public List<Map<String, Object>> findTeacherAchievement(int tid) {
		List<Teachers> tlist = tdao.findAllTeacher(0);
		List<Map<String, Object>> list = new ArrayList<>();
		for (Teachers t : tlist) {
			String typename = tdao.oneType(tid);
			Map<String, Object> map = tdao.achievement(tid);
			map.put("typename", typename);
			list.add(map);
		}
		return list;
	}*/
	
	

}
