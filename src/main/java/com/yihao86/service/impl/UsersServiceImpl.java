package com.yihao86.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihao86.dao.TeachersDao;
import com.yihao86.dao.UsersDao;
import com.yihao86.pojo.Users;
import com.yihao86.pojo.Videos;
import com.yihao86.service.UsersService;

@Service
@Transactional
@AutoConfigureAfter({ UsersDao.class })
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UsersDao udao;

	@Autowired
	public TeachersDao tdao;
	
	@Autowired
	@Qualifier("redisTemplate")
	private RedisTemplate rdt;

	@Override
	public String selectUser(String u_account,String cade, HttpSession session) {
		//String sendMsg = (String) rdt.opsForValue().get("msg"+u_account);
		Users user = udao.findUsers(u_account);
		System.out.println(user);	
		if(user != null) {
			if(u_account.equals(user.getU_account())) {
				if(!cade.equals("123456")) {
					return "error";
				}
			}
		}else { 
			user=new Users();
			int sjs = (int) ((Math.random()*9+1)*100000);
            System.out.println(sjs);
			user.setUid(sjs);
			user.setU_account(u_account);
			user.setU_name(u_account);
			udao.registerUser(user);	
		}
		
		return null;
	}

	@Override
	public Map<String, Object> selectMyFolColl(int uid) {

		return udao.findMyFolColl(uid);
	}

	@Override
	public List<Map<String, Object>> selectUserWatch(int uid) {

		return udao.findUserWatch(uid);
	}

	@Override
	public List<Map<String, Object>> selectCollVideos(int uid) {

		return udao.findCollVideos(uid);
	}

	@Override
	public List<Map<String, Object>> selectUsersCourse(int uid) {

		return udao.findUsersCourse(uid);
	}

	@Override
	public List<Map<String, Object>> selectUserTeachers(int uid) {

		return udao.findUserTeachers(uid);
	}

	@Override
	public Map<Integer, Object> selectTeacherVideo(int uid) {
		Map<Integer, Object> tmap = new HashMap<>();
		List<Map<String, Object>> tids = selectUsetTeacherId(uid);
		for (Map<String, Object> map : tids) {
			for (String item : map.keySet()) {
				int tid = (int) map.get(item);
				List<Videos> vlist = udao.findTeacherVideo(tid);
				tmap.put(tid, vlist);
			}
		}	
		return tmap;
	}

	@Override
	public List<Map<String, Object>> selectUsetTeacherId(int uid) {

		return udao.findUsetTeacherId(uid);
	}

	@Override
	public void deleteTeachers(int uid, int tid) {	
	     udao.deleteTeacher(uid, tid);
	}

}
