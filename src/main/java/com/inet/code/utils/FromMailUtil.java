package com.inet.code.utils;

import cn.hutool.extra.mail.MailAccount;

/**
 * 发送邮件的配置类
 * @author HCY
 * @since 2020-11-16
 */
public class FromMailUtil {
    public static final String HOST = "smtp.163.com";
    public static final String FROM = "huchengyea@163.com";
    public static final String USER = "huchengyea";
    public static final String PASS = "SDZSHTMHUKMVSCRA";
    public static final Integer PORT = 465;
    public static final Boolean SSLENABLE = true;


    /**
     * 设置邮箱的配置文件
     * @author HCY
     * @since 2020-11-16
     * @return MailAccount
     */
    public static MailAccount getMail() {
        MailAccount account = new MailAccount();
        account.setHost(FromMailUtil.HOST);
        account.setPort(FromMailUtil.PORT);
        account.setAuth(true);
        account.setFrom(FromMailUtil.FROM);
        account.setUser(FromMailUtil.USER);
        account.setPass(FromMailUtil.PASS);
        account.setSslEnable(FromMailUtil.SSLENABLE);
        return account;
    }

}
