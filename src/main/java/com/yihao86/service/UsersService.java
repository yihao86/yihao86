package com.yihao86.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yihao86.pojo.Users;
import com.yihao86.pojo.Videos;

public interface UsersService {
 
	public Users selectUser(String u_account);
	
	public Map<String,Object> selectMyFolColl(int uid);
	
	public List<Map<String,Object>> selectUserWatch(int uid);
	
	public List<Map<String,Object>> selectCollVideos( int uid);
	
	public List<Map<String,Object>> selectUsersCourse(int uid);
	
    public List<Map<String,Object>> selectUserTeachers(int uid);
    
    public List<Map<String,Object>> selectUsetTeacherId(int uid);
    
    public Map<Integer,Object> selectTeacherVideo(int uid);
    
    public void deleteTeachers(int uid,int tid);
}
