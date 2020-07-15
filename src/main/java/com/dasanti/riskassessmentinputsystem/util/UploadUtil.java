package com.dasanti.riskassessmentinputsystem.util;

import cn.hutool.core.util.IdUtil;
import org.springframework.http.HttpRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;


public class UploadUtil {
    public static String fileIpload(MultipartFile file){
        String oldName = file.getOriginalFilename();
        String UUID = IdUtil.randomUUID();
        String newName = UUID+oldName;
        String path = "D:/uploadFile";

        // 新建upload文件夹
        File parentPath = new File(path);
        if(!parentPath.exists()) {
            parentPath.mkdirs();
        }
        String src = "";
        try{
            file.transferTo(new File(parentPath,newName));
            File theFile = new File(parentPath+"/"+newName);
            if (theFile.exists()){
                src="/"+newName;
            }else {
                src="";
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return src;
    }
}
