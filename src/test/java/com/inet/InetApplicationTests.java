package com.inet;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.inet.code.entity.production.vo.ProductionUsersView;
import com.inet.code.mapper.ProductionMapper;
import com.inet.code.utils.DateUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class InetApplicationTests {

    @Resource
    private ProductionMapper productionMapper;

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

    @Test
    void test_6(){
        NotIf(1000);
    }

    private Integer NotIf(Integer i) {
        System.out.println(i);
        i--;
        int x;
        try{
            x = 1 / i;
        }catch (Exception e){
            return null;
        }
        NotIf(i);
        return x;
    }

    @Test
    void test_7(){
        String[] day = DateUtils.getBeforeSevenDay();
        for (String date : day){
            System.out.println(date);
        }
    }

    @Test
    void test_8(){
        System.out.println(UUID.randomUUID());
    }

    @Test
    void test_9(){
        System.out.println(2&4);
    }

    @Test
    void test_10(){
        IPage<ProductionUsersView> iPage = productionMapper.getUserPage(new Page<>(1, 5), "1945489676@qq.com", false);
        System.out.println(iPage.getRecords());
        System.out.println(iPage.getPages());
        System.out.println(iPage.getTotal());
        System.out.println(iPage.getSize());
        System.out.println(iPage.getCurrent());
    }

    @Test
    void test_11(){
        System.out.println(StrUtil.isBlank(""));
        System.out.println(StrUtil.isBlank(null));
        System.out.println(StrUtil.isBlank(" "));
    }
}
