package com.haiqing.conference.dao;

import com.haiqing.conference.pojo.Personnel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created By haiqing
 * Date: 2019/3/27
 * Time: 10:22
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonnelMapperTest {
    @Autowired
    private PersonnelMapper personnelMapper;
    private static Personnel personnel;
    static {
         personnel =new Personnel();
         personnel.setName("张三");
         personnel.setDepartment("移动互联网部");
         personnel.setProfession("");
         personnel.setSex((byte)0);
         personnel.setCreateAt(0L);
         personnel.setCreateBy(0);
         personnel.setUpdataAt(0L);
         personnel.setUpdataBy(0);


     }



    @Test
    public void deleteByPrimaryKey() {



    }

    @Test
    public void insert() {
        System.out.println(personnelMapper.insert(personnel)+"///////////////////////");


    }

    @Test
    public void selectByPrimaryKey() {
        System.out.println(personnelMapper.selectByPrimaryKey(1));
    }

    //@Test
    //public void selectAll() {
    //    System.out.println(personnelMapper.selectAll());
    //}

    @Test
    public void updateByPrimaryKey() {






    }
}