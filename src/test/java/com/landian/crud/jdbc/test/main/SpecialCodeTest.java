package com.landian.crud.jdbc.test.main;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 */
public class SpecialCodeTest extends BaseServiceTest {

    private static final Logger logger = Logger.getLogger(SpecialCodeTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void test1(){
        // INSERT INTO sys_user (loginName, userName) VALUES ('李', '~`/><,.!@#$%^&*()_+'"')
        // INSERT INTO sys_user (loginName, userName) VALUES (?, ?)
//        String insertSql = "insert into test(name) values (?)";
        String insertSql = "INSERT INTO sys_user (loginName, userName) VALUES (?, ?)";
        int count = jdbcTemplate.update(insertSql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setObject(1, "name4");
                pstmt.setObject(2, "~`/><,.!@#$%^&*()_+'\"");
            }});
        logger.info(count);
    }


}
