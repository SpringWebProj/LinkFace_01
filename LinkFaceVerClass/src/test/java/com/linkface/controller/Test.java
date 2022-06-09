package com.linkface.controller;

import static org.junit.Assert.fail;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.linkface.config.RootConfig;
import com.linkface.config.SecurityConfig;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class,SecurityConfig.class})
@Log4j
public class Test {
	
	@Setter(onMethod_=@Autowired)
	private DataSource dataSource;
	
	@Setter(onMethod_=@Autowired)
	private SqlSessionFactory sqlSessionFactory;
	
	
	@org.junit.Test
	public void Testcode(){
		
		try ( SqlSession session = sqlSessionFactory.openSession();
				Connection con = session.getConnection()){
			
			log.info(session);
			log.info(con);
		}
		catch(Exception e){
			fail(e.getMessage());
		}
	}
	
	
	
	

}
