package com.yihao86.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TestController {

    @RequestMapping("/index")
    public String test(Model mod){
        mod.addAttribute("index","a");
        return "fileUpload";
    }

    /**
     * 获取yml文件中自定义属性
     */
    @Value("${Mydir.serverURI}")
    private String fileDir;

    /**
     * 文件上传
     * @param request
     * @param files
     * @return
     * @throws IOException
     */
    @RequestMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile[] files) throws IOException{
        System.out.println("进来了");

        List<String> list = new ArrayList<String>();
        // 获得项目的路径
        //ServletContext sc = request.getSession().getServletContext();
        // 上传位置
        String path = fileDir+"壁虎老师/专辑1/课程1/第一章/"; // 设定文件保存的目录
        File f = new File(path);
        
        if (!f.exists())
            f.mkdirs();
        for (int i = 0; i < files.length; i++) {
            // 获得原始文件名
            String fileName = files[i].getOriginalFilename();
            fileName = URLDecoder.decode(fileName, "utf-8");            
            if (!files[i].isEmpty()) {
                try {
        			MultipartFile file = files[i];
        			file.transferTo(new File(path+fileName));               	           
                    //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
        		    /* FileOutputStream fos = new FileOutputStream(path
                            + fileName);
                    InputStream in = files[i].getInputStream();
                    int b = 0;
                    while ((b = in.read()) != -1) {
                        fos.write(b);
                    }
                    fos.close();
                    in.close();*/
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println("上传图片到:" + path + fileName);
            list.add(path + fileName);

        }
        return "video";
    }
    
    
    
    
    
    
    
    /**
     * 文件下载功能
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/down")
    public void down(String fname,HttpServletRequest request,HttpServletResponse response) throws Exception{
        //模拟文件，myfile.txt为需要下载的文件
        String fileName =fileDir+fname;
        //获取输入流
        InputStream bis = new BufferedInputStream(new FileInputStream(new File(fileName)));
        //假如以中文名下载的话
        String filename = "下载文件.txt";
        //转码，免得文件名中文乱码
        filename = URLEncoder.encode(filename,"UTF-8");
        //设置文件下载头
        response.addHeader("Content-Disposition", "attachment;filename=" + filename);
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
        response.setContentType("multipart/form-data");
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        int len = 0;
        while((len = bis.read()) != -1){
            out.write(len);
            out.flush();
        }
        out.close();
    }

    /**
     * 获取图片位置，即磁盘位置
     */
    @Value("${getPic.serverURI}")
    private String getDir;
    /**
     * 获取所有图片
     * @return
     */
    @RequestMapping("getPic")
    public String getPic(Model mod){
        List<String> list = getFiles(getDir);
        System.out.println(getDir);
        mod.addAttribute("list",list);
        return "fileUpload";
    }
    /**
     * 递归获取某路径下的所有文件，文件夹，并输出
     */
    public static List<String> getFiles(String path) {
        File file = new File(path);
        List<String> pname = new ArrayList<>();
        // 如果这个路径是文件夹
        if (file.isDirectory()) {
            // 获取路径下的所有文件
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                // 如果还是文件夹 递归获取里面的文件 文件夹
                if (files[i].isDirectory()) {
                    System.out.println("目录：" + files[i].getPath());
                    getFiles(files[i].getPath());
                } else {
                    System.out.println("文件：" + files[i].getName());
                    pname.add(files[i].getName());
                }
            }
        } else {
            System.out.println("文件：" + file.getPath());
        }
        System.out.println(pname.size());
        return pname;
    }
    
    @RequestMapping("/hello")
    public String go() {
    	
    	return "type";
    }
}
