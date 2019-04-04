package com.haiqing.conference;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created By haiqing
 * Date: 2019/3/28
 * Time: 10:11
 */
@SpringBootApplication
@MapperScan("com.haiqing.conference.dao")
public class ConferenceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConferenceApplication.class,args);
    }
}
