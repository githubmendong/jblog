package com.poscodx.jblog.config;

import com.poscodx.jblog.config.app.DBConfig;
import com.poscodx.jblog.config.app.MyBatisConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan({"com.poscodx.jblog.service", "com.poscodx.jblog.repository"})
@Import({DBConfig.class, MyBatisConfig.class}) //클로스를 가져오는 명시적인 역할
public class AppConfig {

}