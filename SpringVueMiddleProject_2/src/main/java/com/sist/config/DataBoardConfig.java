package com.sist.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/*
	셋팅 => 인식 (DispacherServlet)
				DispacherServlet
	주문 ===========> 서빙 ===========> 주방 (Model)
	배달 ===========> 음식 <=========== HandlerMapping
		ViewResolver
		| 직접 => JSP 생성 없이 처리
		| 간접 => 새로운 JSP 생성
	================================================
	 JSP : Front / Back => 매칭이 어렵다. => HTML / SQL(JPA)
	 @Table(name="board")
	 class Board
	 {
	 	@Id
	 	private int no;
	 }
	 
	 Mobx => React (spring)
 */

@Configuration

@ComponentScan(basePackages = "com.sist.*")
// <context:component-scan base-package="com.sist.*"/>

@MapperScan(basePackages = {"com.sist.mapper"})
// <mybatis-spring:scan base-package="com.sist.mapper" factory-ref="ssf"/>

@EnableAspectJAutoProxy
// <aop:aspectj-autoproxy/>
public class DataBoardConfig implements WebMvcConfigurer{
	
	// => HandlerMapping
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	/*
	 		<bean id="viewResolver"
				class="org.springframework.web.servlet.view.InternalResourceViewResolver"
				p:prefix="/"
				p:suffix=".jsp"
			/>
	 */
	@Bean("viewResolver")
	public ViewResolver viewResolver()
	{
		InternalResourceViewResolver ir=new InternalResourceViewResolver();
		ir.setPrefix("/");
		ir.setSuffix(".jsp");
		return ir;
	}
	
	// 파일 크기 올려놓기 (안하면 5MB까지밖에 안올라간다.)
	@Bean("multipartResolver")
	public CommonsMultipartResolver multipartResolver()
	{
		CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver();
		multipartResolver.setDefaultEncoding("UTF-8");
		multipartResolver.setMaxUploadSizePerFile(100*1024*1024); // 100MB
		return multipartResolver;
	}
	
	/*
	 		<bean id="ds" class="org.apache.commons.dbcp.BasicDataSource"
				p:driverClassName="#{db['driver']}"
				p:url="#{db['url']}"
				p:username="#{db['username']}"
				p:password="#{db['password']}"
				p:maxActive="#{db['maxActive']}"
				p:maxIdle="#{db['maxIdle']}"
				p:maxWait="#{db['maxWait']}"
			/>
	 */
	@Bean("ds")
	public DataSource dataSource()
	{
		BasicDataSource ds=new BasicDataSource();
		ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		ds.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		ds.setUsername("hr");
		ds.setPassword("happy");
		ds.setMaxActive(50);
		ds.setMaxIdle(20);
		ds.setMaxWait(-1);
		return ds;
	}
	
	/*
	 		<bean id="ssf"
				class="org.mybatis.spring.SqlSessionFactoryBean"
				p:dataSource-ref="ds"
			/>
	 */
	@Bean("ssf")
	public SqlSessionFactory sqlSessionFactory() throws Throwable
	{
		SqlSessionFactoryBean ssf=new SqlSessionFactoryBean();
		ssf.setDataSource(dataSource());
		return ssf.getObject();
	}
	
}