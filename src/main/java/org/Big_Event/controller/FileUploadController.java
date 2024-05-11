package org.Big_Event.controller;

import org.Big_Event.pojo.Result;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@Validated
public class FileUploadController {

    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws IOException {
        //把文件的内容存到本地磁盘上？？
        String originalFilename = file.getOriginalFilename();
        //保证文件名字唯一，防止文件覆盖
        //在文件名后拼上uuid字符串
        String filename = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));

        file.transferTo(new File("D:/JavaProjects/LearningMaterials/files" + originalFilename));

        //不能直接传入success:应该是访问在三方服务器上的url地址 而不是访问本地磁盘的url地址
        return Result.success("服务器上的url地址");

    }
}
