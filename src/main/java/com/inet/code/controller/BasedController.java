package com.inet.code.controller;


import com.inet.code.entity.user.dto.UserLandingDomain;
import com.inet.code.entity.user.dto.UserLoginDomain;
import com.inet.code.realize.BasedService;
import com.inet.code.utils.FileUtils;
import com.inet.code.utils.Result;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 基本操作,管理员和用户都需要的操作
 *
 * @author HCY
 * @since 2020/12/11 下午 06:17
*/
@RestController
@CrossOrigin
@RequestMapping("/base")
@Api(tags = {"管理和用户页面的基础操作"},description = "通用模块")
public class BasedController {

    @Resource
    private BasedService basedService;

    /**
     * 登录的API
     *
     * @author HCY
     * @since 2020/12/11 下午 08:01
     * @param userLoginDomain: 登录的DTO端口 , 含有账号和密码
     * @return com.inet.code.utils.Result
    */
    @ApiOperation("登录操作，不需要token")
    @PostMapping("/login")
    public Result postLogin(@RequestBody UserLoginDomain userLoginDomain){
        return basedService.getLogin(userLoginDomain , "/scratch/based/login");
    }

    /**
     * 登出的API
     *
     * @author HCY
     * @since 2020/12/11 下午 08:02
     * @param token: 令牌
     * @return com.inet.code.utils.Result
    */
    @ApiOperation("退出操作")
    @GetMapping("/logout")
    @RequiresRoles(logical = Logical.OR,value = {"admin","member"})
    public Result getLogout(@RequestHeader(value = "Token",defaultValue = "") String token){
        return basedService.getLogout(token,"/scratch/based/logout");
    }

    /**
     * 查看所有类别的API
     *
     * @author HCY
     * @since 2020/12/11 下午 08:02
     * @return com.inet.code.utils.Result
    */
    @ApiOperation("查看所有类别")
    @GetMapping("/listType")
    @RequiresRoles(logical = Logical.OR,value = {"admin","member"})
    public Result getListType(){
        return basedService.getListType("/scratch/based/listType");
    }

    /**
     * 查看所有的标签
     * @author HCY
     * @since 2020/12/11 下午 08:49
     * @return com.inet.code.utils.Result
    */
    @ApiOperation("查看所有的标签")
    @GetMapping("/listLabel")
    @RequiresRoles(logical = Logical.OR,value = {"admin","member"})
    public Result getListLabel(){
        return basedService.getListLabel("/scratch/based/listLabel");
    }

    /**
     * 使用验证码登陆时，获取到验证码
     *
     * @author HCY
     * @since 2020/12/14 4:49 下午
     * @param email: 用户邮箱
     * @return com.inet.code.utils.Result
    */
    @ApiOperation("通过邮箱发送验证码(验证码登陆)，不需要token，不能使用")
    @ApiImplicitParams({
            @ApiImplicitParam(name="email",value="邮箱",dataType="String", paramType = "query"),
    })
    @GetMapping("/verificationLanding")
    public Result getVerificationLanding(@RequestParam(value = "email",defaultValue = "") String email){
        return basedService.getVerificationLanding(email,"/scratch/based/verificationLanding");
    }

    /**
     * 邮箱验证码登陆
     *
     * @author HCY
     * @since 2020/12/14 5:43 下午
     * @param userLandingDomain: 邮箱验证码登陆
     * @return com.inet.code.utils.Result
    */
    @ApiOperation("邮箱验证码登陆(不需要token)，不能使用")
    @PostMapping("/landing")
    public Result postLanding(@RequestBody UserLandingDomain userLandingDomain){
        return basedService.getLanding(
                 userLandingDomain
                ,"/scratch/based/verificationLanding");
    }

    /**
     * 上传文件
     *
     * @author HCY
     * @since 2020/12/14 1:35 下午
     * @param file: 文件
     * @return com.inet.code.utils.Result
     */
    @ApiOperation("只能在 scratch 编译页面进行上传操作")
    @PostMapping(value = "/uploadFiles")
    @RequiresRoles(logical = Logical.OR,value = {"admin","member"})
    public Result getUploadFiles(@RequestParam MultipartFile file){
        String pathUrl = "/scratch/based/uploadFiles";
        //判断文件是否不存在
        if (file.isEmpty()){
            return new Result().result404("文件未找到",pathUrl);
        }
        //文件名
        String fileName = file.getOriginalFilename();
        //后缀名
        String suffixName = ".sb3";
        //设置文件的上传位置
        String path = FileUtils.UPLOAD_SB3_FILE_PATH;
        String url = FileUtils.SB3_URL;
        //设置新的文件名字
        fileName = UUID.randomUUID().toString() + suffixName;
        File dest = new File(path + "/" + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        //判断是否上传成功
        String network = null;
        try {
            file.transferTo(dest);
            network = url + fileName;
            Map<String, String> map = new HashMap<>(2);
            map.put("info","上传成功");
            map.put("url",network);
            return new Result().result200(map,pathUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Result().result500("上传失败",pathUrl);
    }

    /**
     * 上传文件
     *
     * @author HCY
     * @since 2020/12/23 5:51 下午
     * @param file: 上传文件
     * @return com.inet.code.utils.Result
    */
    @ApiOperation("上传图片的base64编码格式，仅限 scratch 编译页面上传")
    @PostMapping(value = "/uploadImages")
    @RequiresRoles(logical = Logical.OR,value = {"admin","member"})
    public Result postUploadImages(@RequestParam String file){
        String path = "/scratch/based/uploadImages";
        //base64格式前头
        String dataPrix = "";
        //实体部分数据
        String data = "";
        if(file==null||"".equals(file)){
            return new Result().result401("上传失败，上传图片数据为空",path);
        }else {
            //将字符串分成数组
            String [] d = file.split("base64,");
            if(d != null && d.length == 2){
                dataPrix = d[0];
                data = d[1];
            }else {
                return new Result().result401("上传失败，数据不合法",path);
            }
        }
        //图片后缀，用以识别哪种格式数据
        String suffix = "";
        //data:image/jpeg;base64,base64编码的jpeg图片数据
        if("data:image/jpeg;".equalsIgnoreCase(dataPrix)){
            suffix = ".jpg";
        }else if("data:image/x-icon;".equalsIgnoreCase(dataPrix)){
            //data:image/x-icon;base64,base64编码的icon图片数据
            suffix = ".ico";
        }else if("data:image/gif;".equalsIgnoreCase(dataPrix)){
            //data:image/gif;base64,base64编码的gif图片数据
            suffix = ".gif";
        }else if("data:image/png;".equalsIgnoreCase(dataPrix)){
            //data:image/png;base64,base64编码的png图片数据
            suffix = ".png";
        }else {
            return new Result().result401("上传图片格式不合法",path);
        }
        String uuid =  UUID.randomUUID().toString();
        String tempFileName=uuid+suffix;
        //新生成的图片
        String imgFilePath = FileUtils.UPLOAD_REST_FILE_PATH + "/" + tempFileName;
        Base64.Decoder decoder = Base64.getDecoder();
        try {
            //Base64解码
            byte[] b = decoder.decode(data) ;
            for(int i=0;i<b.length;++i) {
                if(b[i]<0) {
                    //调整异常数据
                    b[i]+=256;
                }
            }
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            String imgUrl=FileUtils.REST_URL + tempFileName;
            return new Result().result200(imgUrl,path);
        } catch (IOException e) {
            return new Result().result500("错误",path);
        }
    }

    /**
     * 通过token返回用户的信息
     *
     * @author HCY
     * @since 2020/12/19 下午 05:53
     * @param token: 令牌
     * @return com.inet.code.utils.Result
    */
    @ApiOperation("通过token返回用户的信息")
    @GetMapping(value = "/interaction")
    @RequiresRoles(logical = Logical.OR,value = {"admin","member"})
    public Result getInteraction(@RequestHeader(value = "Token",defaultValue = "") String token){
        return basedService.getInteraction(token,"/scratch/user/interaction");
    }

    /**
     * 使用上传组件上传图片
     *
     * @author HCY
     * @since 2021/1/8 下午1:48
     * @param file: 上传的文件
     * @return com.inet.code.utils.Result
    */
    @ApiOperation("使用上传组件上传图片")
    @PostMapping("/uploading")
    @RequiresRoles(logical = Logical.OR,value = {"admin","member"})
    public Result postUploading(@PathVariable MultipartFile file){
        return FileUtils.getUploading(file,"/scratch/user/uploading");
    }

}
