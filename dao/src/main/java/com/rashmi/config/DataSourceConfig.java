package com.rashmi.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

//this file is not used. Datasource values are loaded from organization.properties file
@Configuration
public class DataSourceConfig{

    public static final String REQ_CONF = "beans-cp.xml";

    @Bean
    public DataSource dataSource(){

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/makeup_artist_application_db?useSSL=false");
        dataSource.setUsername("");
        dataSource.setPassword("");
        return dataSource;
    }

}
