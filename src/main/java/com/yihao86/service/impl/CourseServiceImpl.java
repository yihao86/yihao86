package com.yihao86.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.artemis.ArtemisNoOpBindingRegistry;
import org.springframework.stereotype.Service;

import com.yihao86.dao.CourseDao;
import com.yihao86.dao.TeachersDao;
import com.yihao86.pojo.Videos;
import com.yihao86.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseDao cdao;
	@Autowired
	private TeachersDao tdao;
	@Override
	public List<Videos> fandOneCourse(int crid) {
		return cdao.fandOneCourse(crid);
	}
	@Override
	public List<String> fandChapter(int crid) {
		List<Videos> vlist = cdao.fandOneCourse(crid);
		String path2 = null;
		List<String> list = new ArrayList<String>();
		for (Videos v : vlist) {
			String path = v.getV_vpath();
			String path1 = path.substring(0,path.lastIndexOf("/"));	
			path2 = path1.substring(path1.lastIndexOf("/")+1);
			list.add(path2);
		}
		 for(int i =  0;i < list.size() - 1;i++){       
		      for(int j = list.size() -  1;j > i ;j--){       
		           if(list.get(j).equals(list.get(i))){       
		              list.remove(j);       
		           }        
		        }        
		      } 
		return list;
	}
	
	@Override
	public Map<String,Object> fandOneChapter(int crid) {
		List<String> list = fandChapter(crid);
		List<Videos> vlist = new ArrayList<Videos>();
		Map<String,Object> map = new HashMap<>();
		for (String s : list) {
			vlist = cdao.fandChapter(s);
			for (Videos v : vlist) {
				int num = tdao.viewingNumber(v.getVid());
				map.put(v.getV_name(), num);
			}
	        map.put(s, vlist);	
		}	
		return map;
	}
	
	
	@Override
	public Map<String, Object> fandCourseInfo(int crid) {
		return cdao.fandCourseInfo(crid);
	}

}
