package com.wch.uwb;

import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

//ServletComponentScan过滤器
@SpringBootApplication
@MapperScan("com.wch.uwb.mapper")
@ServletComponentScan
public class UwbApplication {

	public static void main(String[] args) {
		SpringApplication.run(UwbApplication.class, args);

	}

}
