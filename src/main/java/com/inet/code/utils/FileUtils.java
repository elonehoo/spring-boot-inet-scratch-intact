package com.inet.code.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * FileUtils
 *
 * @author HCY
 * @since 2020/11/21 下午 06:42
 */
public class FileUtils {

    /**
    * sb3文件上传的路径
    * @author HCY
    * @since 2020/11/22 下午 12:12
    */
    private static final String UPLOAD_SB3_FILE_PATH = "/home/inet/soft/tomcat/apache-tomcat-9.0.41/webapps/sb3";
    private static final String UPLOAD_REST_FILE_PATH = "/home/inet/soft/tomcat/apache-tomcat-9.0.41/webapps/images";

    private static final String SB3_URL = "http://47.99.145.161:8080/sb3/";
    private static final String REST_URL = "http://47.99.145.161:8080/images/";

    /**
    * 上传文件
    * @author HCY
    * @since 2020/11/22 下午 12:13
    * @param file: 文件
    * @return java.lang.String
    */
    public static Result getUploading(MultipartFile file, String pathUrl) {
        //判断文件是否不存在
        if (file.isEmpty()){
            return new Result().result404("文件未找到",pathUrl);
        }
        //文件名
        String fileName = file.getOriginalFilename();
        //后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //设置文件的上传位置
        String path = FileUtils.UPLOAD_SB3_FILE_PATH;
        String url = FileUtils.SB3_URL;
        //如果不是sb3文件
        if (! ".sb3".equals(suffixName)){
            path = FileUtils.UPLOAD_REST_FILE_PATH;
            url = FileUtils.REST_URL;
        }
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
            return new Result().result200(map,path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Result().result500("上传失败",path);
    }
}
