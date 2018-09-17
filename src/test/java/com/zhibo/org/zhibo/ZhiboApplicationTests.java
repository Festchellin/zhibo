package com.zhibo.org.zhibo;

import com.zhibo.org.zhibo.util.StringGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZhiboApplicationTests {

    @Test
    public void contextLoads() {
        System.out.println(StringGenerator.UUIDGenerator());
    }

}
