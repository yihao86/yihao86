package com.yihao86.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yihao86.pojo.Teachers;
import com.yihao86.pojo.Users;
import com.yihao86.pojo.Videos;

public interface UsersDao {
	
	public Users findUsers(@Param("u_account") String u_account);
	
	public Map<String,Object> findMyFolColl(@Param("uid") int uid);
	
	public List<Map<String,Object>> findUserWatch(@Param("uid") int uid);
	
	public List<Map<String,Object>> findCollVideos(@Param("uid") int uid);
	
	public  List<Map<String,Object>> findUsersCourse(@Param("uid") int uid); 
	
	public List<Map<String,Object>> findUserTeachers(@Param("uid") int uid);
	
	public List<Map<String,Object>> findUsetTeacherId(@Param("uid") int uid);	
	
	public List<Videos> findTeacherVideo(@Param("tid") int tid);
	
	public void deleteTeacher(@Param("uid") int uid,@Param("tid")int tid);
}
