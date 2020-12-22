package com.inet;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

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

    @Test
    void test_2(){
        int a = 1;
        int total = a % 5 != 0 ? (a / 5 + 1) : (a / 5);
        System.out.println(total);
    }

    @Test
    void test_3(){
        Boolean a = true;
        if (a != null){
            System.out.println("1");
        }else {
            System.out.println("2");
        }
    }

    @Test
    void test_4(){
        System.out.println("123 ==> " + reverse(123));
        System.out.println("-123 ==> " + reverse(-123));
        System.out.println("120 ==> " + reverse(120));
    }

    public int reverse(int x) {
        int ans = 0;
        while (x != 0) {
            int pop = x % 10;
            if (ans > Integer.MAX_VALUE / 10 || (ans == Integer.MAX_VALUE / 10 && pop > 7))
                return 0;
            if (ans < Integer.MIN_VALUE / 10 || (ans == Integer.MIN_VALUE / 10 && pop < -8))
                return 0;
            ans = ans * 10 + pop;
            x /= 10;
        }
        return ans;
    }

    @Test
    void test_5(){
        System.out.println(DigestUtil.md5Hex("hcy05080901"));
    }
}
