package com;

import javax.servlet.MultipartConfigElement;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@MapperScan(basePackages="com.dao")
@SpringBootApplication
public class MyApplication extends SpringBootServletInitializer {
	
	
	/*@Bean
	public MultipartConfigElement multipartConfigElement() {
	  MultipartConfigFactory factory = new MultipartConfigFactory();
	  //单个文件最大
	  factory.setMaxFileSize("1024MB");
	  /// 设置总上传数据总大小
	  factory.setMaxRequestSize("2048MB");
	  return factory.createMultipartConfig();
	}*/
	
	/*@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(MyApplication.class);
	}	
	*/
	public static void main(String[] args) {
		SpringApplication sa = new SpringApplication(MyApplication.class);
		sa.run(args);
	}
}
