package com.example;

import com.example.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

/**
 * Created by miki on 15. 10. 10..
 */
@EnableAutoConfiguration
public class App implements CommandLineRunner {
    /*
     * autoconfigure를 통해 DataSource나 JdbcTemplate, NamedParameterJdbcTemplate을 자동으로 생성하여 DI container에 등록함
     * DB 의존 관계를 설정하지 않았다면 In Memory Database가 만들어짐(여기서는 H2)
     */
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public void run(String... strings) throws Exception {
        String sql = "SELECT id, first_name, last_name FROM customers WHERE id = :id";
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", 1);
        Customer result = jdbcTemplate.queryForObject(sql, param, (resultSet, rowNum) -> {
                return new Customer(
                        resultSet.getInt("id"), resultSet.getString("first_name"), resultSet.getString("last_name"));
        });

        System.out.println("result = " + result);
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
