package com.citictel.report.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DataSourceConfiguration {

	@Bean
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource primaryDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "secondDatasource")
	@ConfigurationProperties(prefix = "spring.second-datasource")
	public DataSource secondDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = "sqlserverDatasource")
	@ConfigurationProperties(prefix = "spring.sqlserver-datasource")
	public DataSource sqlServerDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = "cdrTestDatasource")
	@ConfigurationProperties(prefix = "spring.oracle-cdr-test")
	public DataSource oracleDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = "cdrDatasource")
	@ConfigurationProperties(prefix = "spring.oracle-cdr")
	public DataSource cdrDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	
}