package com.inet.code.controller.username;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户的基本操作
 *
 * @author HCY
 * @since 2020/12/11 下午 06:16
*/
@RestController
@CrossOrigin
@RequestMapping("/user")
@Api(tags = {"用户的基本操作模块"},description = "用户模块")
public class UserBasedController {

}
