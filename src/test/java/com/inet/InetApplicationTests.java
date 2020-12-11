package com.inet;

import com.inet.code.entity.dto.user.UserBaseDomain;
import com.inet.code.entity.po.User;
import com.inet.code.utils.CloneUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class InetApplicationTests {

    @Test
    void contextLoads() {
        User user = new User("1","2","3","4",true,new Date(),"5","6",new Date(),new Date());
        List<User> list = new ArrayList<>();
        list.add(user);

        List<UserBaseDomain> list1 = CloneUtil.batchClone(list,UserBaseDomain.class);
        System.out.println(list1);
    }

}
