package com.yihao86.pojo;

import lombok.Getter;
import lombok.Setter;

//每周一练
@Getter
@Setter
public class Works {
	private int wid;//练习作品id
	private int w_tid;//教师id
	private String w_name;//作品名称
	private String w_path;//作品路径
	private String w_introduce;//作品介绍
	private String w_datetime;//活动时间
	private String w_img;//作品图片
	
}
