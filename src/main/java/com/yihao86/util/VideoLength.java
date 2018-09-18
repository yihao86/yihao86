package com.yihao86.util;

import java.io.File;

import org.springframework.stereotype.Component;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;

@Component
public class VideoLength {

	public static String ReadVideoTime(File source) {
		Encoder encoder = new Encoder();
		String length = "";
		try {
			System.out.println(source);
		MultimediaInfo m = encoder.getInfo(source);
		long ls = m.getDuration()/1000;
		int hour = (int) (ls/3600);
		int minute = (int) (ls%3600)/60;
		int second = (int) (ls-hour*3600-minute*60);
		String min = "";
		String sec = "";
		if(minute < 10) {
			min = "0"+minute;
		}
		if(second < 10)
			sec = "0"+second;
		length = hour+"时"+min+"分"+sec+"秒";
		} catch (Exception e) {
		e.printStackTrace();
		}
		return length;
		}
	
	
}
