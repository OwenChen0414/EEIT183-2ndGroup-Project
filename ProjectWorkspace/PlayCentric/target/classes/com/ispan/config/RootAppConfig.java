package com.ispan.config;

import java.util.Properties;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.dialect.SQLServerDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = {"com.ispan"})
@EnableTransactionManagement
public class RootAppConfig {
	
	@Bean
	public DataSource dataSource() throws IllegalArgumentException, NamingException {
		JndiObjectFactoryBean jfb = new JndiObjectFactoryBean();
		jfb.setJndiName("java:comp/env/connectSQLServer/SystemService");
//		jfb.setJndiName("java:comp/env/connectMySQL/SystemService");
		jfb.afterPropertiesSet();
		DataSource ds = (DataSource) jfb.getObject();
		return ds;
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() throws IllegalArgumentException, NamingException {
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		factoryBean.setDataSource(dataSource());
		factoryBean.setPackagesToScan("com.ispan");
		factoryBean.setHibernateProperties(addtionalProperties());
		return factoryBean;
	}
	
	private Properties addtionalProperties() {
		Properties props = new Properties();
		props.put("hibernate.dialect", SQLServerDialect.class);
		props.put("hibernate.show_sql", Boolean.TRUE);
		props.put("hibernate.format_sql", Boolean.TRUE);
		return props;
	}
	
	@Bean
	public HibernateTransactionManager transactionManager() throws IllegalArgumentException, NamingException {
		HibernateTransactionManager txMgr = new HibernateTransactionManager();
		txMgr.setSessionFactory(sessionFactory().getObject());
		return txMgr;
	}
	
}
