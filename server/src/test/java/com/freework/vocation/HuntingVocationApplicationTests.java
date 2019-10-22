package com.freework.vocation;

import com.freework.vocation.dao.VocationDao;
import com.freework.vocation.entity.Vocation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class HuntingVocationApplicationTests {
    @Autowired(required = false)
    private VocationDao vocationDao;

    @Test
    public void contextLoads() {
        Vocation vocation=new Vocation();
        vocation.setStatus(1);
//        vocation.setVocationCategoryId(8);
//        vocation.setEducation("大专及同等学历");
//        vocation.setExperience("1-3年");
        vocation.setVocationName("java");
        vocation.setPlace("北京");
        List<Vocation> vocationList= vocationDao.queryVocationByRequirement(vocation);
        for (Vocation vocation1 : vocationList) {
            System.out.println(vocation1.getVocationName());
        }
    }

}
