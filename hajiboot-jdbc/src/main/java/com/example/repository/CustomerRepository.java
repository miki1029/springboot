package com.example.repository;

import com.example.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by miki on 15. 10. 10..
 */
@Repository
@Transactional
// DI 컨테이너가 각 메소드 앞뒤에 DB 처리를 추가한 동적 클래스를 생성함. DB 트랜잭션(commit or rollback)이 자동으로 이루어짐.
// checked exception이 발생한 경우 rollback 되지 않음.
public class CustomerRepository {
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;
    SimpleJdbcInsert insert;

    @PostConstruct
    public void init() {
        insert = new SimpleJdbcInsert(
                (JdbcTemplate) jdbcTemplate.getJdbcOperations())    // JdbcTemplate 설정
                .withTableName("customers")                         // INSERT SQL 자동 생성
                .usingGeneratedKeyColumns("id");                    // auto increment 컬럼명
    }

    private static final RowMapper<Customer> customerRowMapper = (resultSet, i) -> {
        Integer id = resultSet.getInt("id");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        return new Customer(id, firstName, lastName);
    };

    public List<Customer> findAll() {
        return jdbcTemplate.query(
                "SELECT id, first_name, last_name FROM customers ORDER BY id",
                customerRowMapper);
    }

    public Customer findOne(Integer customerId) {
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", customerId);
        return jdbcTemplate.queryForObject(
                "SELECT id, first_name, last_name FROM customers WHERE id=:id",
                param, customerRowMapper);
    }

    public Customer save(Customer customer) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(customer); // 필드 이름과 값을 매핑한 파라미터 생성
        if(customer.getId() == null) {
            Number key = insert.executeAndReturnKey(param); // 자동으로 번호가 매겨진 ID 반환
            customer.setId(key.intValue());
            /*jdbcTemplate.update(
                    "INSERT INTO customers(first_name, last_name) VALUES(:firstName, :lastName)",
                    param);*/
        } else {
            jdbcTemplate.update(
                    "UPDATE customers SET first_name=:firstName, last_name=:lastName WHERE id=:id",
                    param);
        }
        return customer;
    }

    public void delete(Integer customerId) {
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", customerId);
        jdbcTemplate.update(
                "DELETE FROM customers WHERE id=:id",
                param);
    }
}
