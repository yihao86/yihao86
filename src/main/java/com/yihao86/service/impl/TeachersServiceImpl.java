package com.yihao86.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yihao86.dao.TeachersDao;
import com.yihao86.pojo.Teachers;
import com.yihao86.pojo.Videos;
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
	public List<String> findAllAchievement(){
		List<Teachers> tlist = tdao.findAllTeacher();
		List<String> list = new ArrayList<>();
		for (Teachers t : tlist) {
			List<Videos> vlist = tdao.teacherVideo(t.getTid());
			for (Videos v : vlist){
				if(vlist.size() < 2) {
					String path = v.getV_imgs();
					list.add(path);
				}
			}
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
	
	

}
