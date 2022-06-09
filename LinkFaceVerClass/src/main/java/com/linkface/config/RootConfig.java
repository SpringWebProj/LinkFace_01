package com.linkface.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableScheduling
@ComponentScan(basePackages = "com.linkface.entity")
@ComponentScan(basePackages = "com.linkface.service")
@ComponentScan(basePackages = "com.linkface.security")
@ComponentScan(basePackages = "com.linkface.util")
@ComponentScan(basePackages = "com.linkface.scheduler")
@MapperScan(basePackages = "com.linkface.mapper")
public class RootConfig {

	@Autowired
	ApplicationContext applicationContext;
	
	
	@Bean
	public DataSource dataSource() {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
		hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/linkface?serverTimezone=Asia/Seoul");
		hikariConfig.setUsername("root");
		hikariConfig.setPassword("1234");
		HikariDataSource dataSource = new HikariDataSource(hikariConfig);
		return dataSource;
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception{
	
		SqlSessionFactoryBean sqlSessionFactoryBean =
				new SqlSessionFactoryBean();
		
		sqlSessionFactoryBean.setDataSource(dataSource());
		sqlSessionFactoryBean.setConfigLocation(
				applicationContext.getResource
				("classpath:/mybatis-config.xml"));
		return (SqlSessionFactory) sqlSessionFactoryBean.getObject();
	
	}
	
}
