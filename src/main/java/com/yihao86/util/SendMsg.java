package com.yihao86.util;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import  com.yihao86.util.HttpUtils;

public class SendMsg {
	
	public static String Send(String phone) {
		
		String host = "https://fesms.market.alicloudapi.com";
	    String path = "/sms/";
	    String method = "GET";
	    String appcode = "228c59cb22374592a39811d62080e34c";
	    Map<String, String> headers = new HashMap<String, String>();
	    //�����header�еĸ�ʽ(�м���Ӣ�Ŀո�)ΪAuthorization:APPCODE 83359fd73fe94948385f570e3c139105
	    headers.put("Authorization", "APPCODE " + appcode);
	    Map<String, String> querys = new HashMap<String, String>();
	    int random = (int)((Math.random()*9+1)*100000);
	    String r = String.valueOf(random);
	    querys.put("code", r);
	    querys.put("phone", phone);
	    querys.put("skin", "20");
	    querys.put("sign", "1");
	    
	    
            //JDK 1.8ʾ�����������������أ�  http://code.fegine.com/Tools.zip
	    try {
	    	
	    	HttpResponse res = HttpUtils.doGet(host, path, method, headers, querys);
	    	//System.out.println(response.toString());
	    	//��ȡresponse��body
	    	System.out.println(EntityUtils.toString(res.getEntity()));
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    return r;
	}

}
