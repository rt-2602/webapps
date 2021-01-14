package com.rashmi.config;

/*
@Configuration
public class DataSourceConfig {

    public static final String REQ_CONF = "beans-cp.xml";

    @Bean
    public DataSource getDataSource() {
        System.out.println("Getting database connection.....");
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
        dataSourceBuilder.url("jdbc:mysql://localhost:3306/makeup_artist_application_db?useSSL=false");
        dataSourceBuilder.username("");
        dataSourceBuilder.password("");
        return dataSourceBuilder.build();
    }
}
*/

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig{

    public static final String REQ_CONF = "beans-cp.xml";

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/makeup_artist_application_db?useSSL=false");
        dataSource.setUsername("devuser");
        dataSource.setPassword("jedan1*");
        return dataSource;
    }

}
