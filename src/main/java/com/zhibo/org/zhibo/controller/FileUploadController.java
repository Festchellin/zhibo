package com.zhibo.org.zhibo.controller;

import com.zhibo.org.zhibo.util.StringGenerator;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/upload")
public class FileUploadController {
    @PostMapping("/oneImg")
    public Object uploadOneImg(MultipartFile file) {
        Map<String, Object> map = new HashMap<>();
        if (file.isEmpty()) {
            map.put("error_code", -1);
            map.put("message", "上传失败，无图片上传");
            return map;
        }
        //获取上传文件名
        String fileName = file.getOriginalFilename();
        //获取后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        fileName = StringGenerator.UUIDGenerator() + suffixName;
        File path;
        File dest;
        try {
            path = new File(ResourceUtils.getURL("classpath:").getPath());
            if (!path.exists()) path = new File("");
            System.out.println("path:" + path.getAbsolutePath());
            File upload = new File(path.getAbsolutePath(), "static/images/avatar/");
            if (!upload.exists()) upload.mkdirs();
            dest = new File(upload.getAbsolutePath() + "/" + fileName);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdir();
            }
            file.transferTo(dest);
            System.out.println(dest.getPath());
            map.put("error_code", 1);
            map.put("message", "上传成功");
            map.put("img_url", "/images/avatar/" + fileName);
            return map;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            map.put("error_code", -1);
            map.put("message", "上传失败，找不到该文件夹");
            return map;
        } catch (IOException e) {
            e.printStackTrace();
            map.put("error_code", -1);
            map.put("message", "上传失败，IO异常");
            return map;
        }
    }

    @PostMapping("/fileUpload")
    public Object singleFileUpload(@RequestParam("file") MultipartFile file){
        Map<String, Object> map = new HashMap<>();
        if (file.isEmpty()) {
            map.put("error_code", -1);
            map.put("message", "上传失败，无文件上传");
            return map;
        }

        try {
            byte[] fileBytes = file.getBytes();
            if (fileBytes.length > 1024*1024*1){
                map.put("error_code", -1);
                map.put("message", "上传失败!!");
                return map;
            }

            String fileName = file.getOriginalFilename();
            Path path = Paths.get(ResourceUtils.getURL("classpath:").getPath().substring(1)+ "static/files/" + fileName);

            Files.write(path, fileBytes);
            map.put("error_code", 1);
            map.put("message", "上传成功");
            map.put("files_url", "/files" + fileName);
            return map;

        } catch (IOException e) {
            e.printStackTrace();
            map.put("error_code", -1);
            map.put("message", "上传失败!!");
            return map;
        }

    }


}
