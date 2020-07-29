package com.dasanti.riskassessmentinputsystem.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.dasanti.riskassessmentinputsystem.entity.Enterprise;
import com.dasanti.riskassessmentinputsystem.service.EnterpriseService;
import com.dasanti.riskassessmentinputsystem.util.ResultEntity;
import com.dasanti.riskassessmentinputsystem.util.UploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Controller
@Slf4j
public class EnterpriseController {
    @Resource
    private EnterpriseService enterpriseService;

    // 获取经纬度
    @RequestMapping("/get/lng/and/lat")
    @ResponseBody
    public ResultEntity<Object> getLngAndLat(@RequestParam String address){
        System.out.println(address);
        HashMap<String,Object> param = new HashMap<>();
        param.put("address",address);
        param.put("output","json");
        param.put("ak","wFQlNo1lSDLEx8RV6vehHiwLKlx23GNg");
        //param.put("callback","showLocation");
        // 发送请求获取百度经纬度
        String result = HttpUtil.get("http://api.map.baidu.com/geocoding/v3/",param);
        JSONObject jsonResult = JSONObject.parseObject(result);
        return ResultEntity.successWithData(jsonResult);
    }
    @RequestMapping("/test/get")
    @ResponseBody
    public Enterprise getEnterprise(@RequestParam Integer id){
        Enterprise enterprise = enterpriseService.getEnterpriseById(id);

        return enterprise;
    }
    @RequestMapping("/myhome")
    public String home(){
        return "/home.html";
    }

    // 保存企业信息
    @RequestMapping("/save/enterprise")
    @ResponseBody
    public ResultEntity<String> insertEnterprise(@RequestBody Enterprise enterprise,HttpSession session){
        try{
            // 保存企业信息并返回自增主键
            Integer id = enterpriseService.saveEnterprise(enterprise);
            // log.info("返回的自增主键：" + id);

            try {
                //Integer id = enterpriseService.getEnterpriseId(enterprise.getCompanyName());
                // 把自增id保存到session
                session.setAttribute("id",id);
            }catch (Exception e){
                e.printStackTrace();
                log.info(e.getMessage());

            }
            return ResultEntity.successWithoutData();
        }catch (Exception e){
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }

    }
    // 保存图片路径
    @RequestMapping("/get/file/path")
    @ResponseBody
    public ResultEntity<String> testFile(@RequestParam MultipartFile file) {
        String src = "";

            if (file != null && file.getName() != null && !file.isEmpty()){
                try{
                    ResultEntity<String> filePath = UploadUtil.fileIpload(file);
                    if(filePath.getResult()=="SUCCESS"){
                        src = "http://127.0.0.1:9000"+"/uploadFile" + filePath.getData();
                    }
                    System.out.println(src);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }


        return ResultEntity.successWithData(src);
    }
}
