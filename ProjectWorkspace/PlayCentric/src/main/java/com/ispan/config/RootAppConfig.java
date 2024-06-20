package com.ispan.config;

import java.util.Properties;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//相當於beans.config.xml的java程式組態
@Configuration
@ComponentScan(basePackages = "com.ispan")
@EnableTransactionManagement
@SuppressWarnings("unused")
public class RootAppConfig {
	private static final String SQLSERVER = "SQLServer";
	private static final String MYSQL = "MySQL";
	
	@Bean
	public DataSource dataSource() throws IllegalArgumentException, NamingException {
		JndiObjectFactoryBean jndiBean = new JndiObjectFactoryBean();
		jndiBean.setJndiName("java:comp/env/connect"+SQLSERVER+"/SystemService");
		jndiBean.afterPropertiesSet();
		return (DataSource)jndiBean.getObject();
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() throws IllegalArgumentException, NamingException {
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		factoryBean.setDataSource(dataSource());
		factoryBean.setPackagesToScan("com.ispan");
		factoryBean.setHibernateProperties(additionalProperties());
		return factoryBean;
	}

	private Properties additionalProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.show_sql", Boolean.TRUE);
		properties.put("hibernate.format_sql", Boolean.TRUE);
		properties.put("hibernate.allow_update_outside_transaction", Boolean.TRUE);
		return properties;
	}
	
	@Bean
	public HibernateTransactionManager transactionManager() throws IllegalArgumentException, NamingException {
		HibernateTransactionManager txMgr = new HibernateTransactionManager();
		txMgr.setSessionFactory(sessionFactory().getObject());
		return txMgr;
	}
}
