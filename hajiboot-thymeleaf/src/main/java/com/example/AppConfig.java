package com.example;

import net.sf.log4jdbc.Log4jdbcProxyDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.sql.DataSource;

/**
 * Created by miki on 15. 10. 11..
 */
@Configuration
public class AppConfig {
    @Autowired
    DataSourceProperties dataSourceProperties; // application.yml의 spring.datasource 속성들을 저장하는 클래스를 주입
    DataSource dataSource;

    @Bean
    DataSource realDataSource() {
        DataSourceBuilder factory = DataSourceBuilder
                .create(this.dataSourceProperties.getClassLoader()) // spring.datasource.driverClassName
                .url(this.dataSourceProperties.getUrl())            // spring.datasource.url
                .username(this.dataSourceProperties.getUsername())  // spring.datasource.username
                .password(this.dataSourceProperties.getPassword()); // spring.datasource.password
        this.dataSource = factory.build();
        return this.dataSource;
    }

    @Bean
    @Primary
    // dataSource Wrapping for Logging
    DataSource dataSource() {
        return new Log4jdbcProxyDataSource(this.dataSource);
    }

    @Order(Ordered.HIGHEST_PRECEDENCE) // Bean 정의의 우선순위(필터 적용 순서로 값이 작은 쪽이 먼저 적용)(Integer.MIN_VALUE)
    @Bean
    CharacterEncodingFilter characterEncodingFilter() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        return filter;
    }
}
