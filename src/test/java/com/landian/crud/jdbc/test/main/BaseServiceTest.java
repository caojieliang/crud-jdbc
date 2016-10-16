package com.landian.crud.jdbc.test.main;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Date: 15/5/19
 * Time: 下午6:44
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
//        "classpath*:spring-dubbo-consumer.xml",
        "classpath*:spring-service.xml",
        "classpath*:spring-dataSource.xml"
})
public class BaseServiceTest {
}
