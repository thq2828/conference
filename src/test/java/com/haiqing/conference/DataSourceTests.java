package com.haiqing.conference;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

/**
 * Created By haiqing
 * Date: 2019/3/26
 * Time: 11:02
 * 测试采用的是什么连接池
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataSourceTests {
    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    DataSourceProperties dataSourceProperties;

    @Test
    public void testDataSourceProperties(){
        DataSource dataSource = applicationContext.getBean(DataSource.class);
        System.out.println(dataSource);
        System.out.println(dataSource.getClass().getName()+"---------------------------------");
        System.out.println(dataSourceProperties);
    }

}
