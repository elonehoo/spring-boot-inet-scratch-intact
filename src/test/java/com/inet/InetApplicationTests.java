package com.inet;

import cn.hutool.core.util.StrUtil;
import com.inet.code.entity.user.dto.UserBaseDomain;
import com.inet.code.entity.user.po.User;
import com.inet.code.utils.CloneUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class InetApplicationTests {

    @Test
    void test_1(){
        String a = "  ";
        String b = null;
        String c = "";
        System.out.println("============================");
        System.out.println(StrUtil.isEmpty(a));
        System.out.println(StrUtil.isEmpty(b));
        System.out.println(StrUtil.isEmpty(c));
        System.out.println("============================");
        System.out.println(StrUtil.isBlank(a));
        System.out.println(StrUtil.isBlank(b));
        System.out.println(StrUtil.isBlank(c));
    }

}
