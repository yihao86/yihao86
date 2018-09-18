package com.yihao86.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yihao86.pojo.History;
import com.yihao86.pojo.Videos;

public interface VideosDao {
	/**
	 * 查询所有
	 * @param vid
	 * @return
	 */
	public List<Map<String,Object>> findAll();

/**
 * 查询单个视频
 * @param vid
 * @return
 */
	public Videos findVideoById(@Param("vid") int vid);
	
	/**
	 * 查询本天的7天前上传的所有视频
	 * @return
	 */
	public  List<Map<Object,String>> findNewestVideo();
	
	/**
	 * 根据子分类查询该分类下的所有视频
	 * @param f_trid
	 * @return
	 */
	public List<Map<Object,String>> findVideosByTid(@Param("typeId") int typeId);
	
	
	public List<Map<Object,String>> findVideosByOne(@Param("f_trid") int f_trid);
	
	List<Map<String,Object>> findBroadcast();
	
	int insertHistory(History history);
}
