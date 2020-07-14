package com.dasanti.riskassessmentinputsystem.controller;


import com.dasanti.riskassessmentinputsystem.entity.DetermineFactors;
import com.dasanti.riskassessmentinputsystem.entity.FactorScore;
import com.dasanti.riskassessmentinputsystem.service.ConcreteEvaluationService;
import com.dasanti.riskassessmentinputsystem.util.ResultEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class ConcreteEvaluationController {
    @Resource
    private ConcreteEvaluationService concreteEvaluationService;

    @RequestMapping("/save/concrete/evaluation")
    public ResultEntity<String> insertConcreteEvaluation(@RequestBody List<DetermineFactors> determineFactorsList, HttpSession session){

        try{
            for(int i=0;i <determineFactorsList.size();i++){

                try{
                    // 获取分数进行保存
                    String scoreName = determineFactorsList.get(i).getDetermineFactor();
                    Integer score = concreteEvaluationService.getScoreByDetermineFactor(scoreName);
                    determineFactorsList.get(i).setScore(score);

                    try{
                        // 获取企业ID
                        Integer id = (Integer) session.getAttribute("id");
                        determineFactorsList.get(i).setEnterpriseId(id);
                        // 保存事故可能性
                        DetermineFactors determineFactors = determineFactorsList.get(i);
                        concreteEvaluationService.saveDetermineFactors(determineFactors);
                    }catch (Exception e){
                        e.printStackTrace();
                    }


                }catch(Exception e){
                    e.printStackTrace();
                }

            }
            return ResultEntity.successWithoutData();
        }catch (Exception e){
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }


    }
    // 保存事故后果
    @RequestMapping("/save/accident/consequence")
    public ResultEntity<String> InsertAccidentConsequence(@RequestBody List<DetermineFactors> determineFactors,HttpSession session){

        try{

            for (int i=0;i <determineFactors.size();i++){

                try{
                    // 获取分数进行保存
                    String scoreName = determineFactors.get(i).getDetermineFactor();
                    Integer score = concreteEvaluationService.getScoreByDetermineFactor(scoreName);
                    determineFactors.get(i).setScore(score);
                    try{
                        // 保存企业id
                        Integer id = (Integer) session.getAttribute("id");
                        determineFactors.get(i).setEnterpriseId(id);

                        // 保存事故后果
                        DetermineFactors determineFactors1 = determineFactors.get(i);
                        concreteEvaluationService.saveDetermineFactors(determineFactors1);
                    }catch (Exception e){
                        e.printStackTrace();
                    }



                }catch (Exception e){
                    e.printStackTrace();
                }

            }
            return ResultEntity.successWithoutData();
        }catch (Exception e){
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }


    }
    // 保存事故暴露程度
    @RequestMapping("/save/employees")
    public ResultEntity<String> saveEmployees(@RequestBody DetermineFactors determineFactors,HttpSession session) {
        try{
            // 获取分数
            String scoreName = determineFactors.getDetermineFactor();
            Integer score = concreteEvaluationService.getScoreByDetermineFactor(scoreName);
            determineFactors.setScore(score);
            // 获取企业信息
            Integer id = (Integer) session.getAttribute("id");
            determineFactors.setEnterpriseId(id);
            try{
                // 保存事故暴露程度
                concreteEvaluationService.saveDetermineFactors(determineFactors);
            }catch(Exception e){
                e.printStackTrace();
            }
            return ResultEntity.successWithoutData();
        }catch (Exception e){
            return  ResultEntity.failed(e.getMessage());
        }
    }
   /* @RequestMapping("/get/photo")
    public ResultEntity<String> getphoto(@RequestParam Integer enterpriseId,@RequestParam Integer influenceFactorId,@RequestParam String determineFactor){
        try{
            String photo = concreteEvaluationService.getPhotoById(enterpriseId,influenceFactorId,determineFactor);
            return ResultEntity.successWithData(photo);
        }catch (Exception e){
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }
    }*/
   // 获取分数值
   @RequestMapping("/get/score")
   public ResultEntity<Object> getScore(@RequestParam Integer enterpriseId){
        try{
            List<DetermineFactors> determineFactorsList = concreteEvaluationService.getDetermineFactorsListById(enterpriseId);
            // List testScore = concreteEvaluationService.getScoreById(enterpriseId,);
            List list = new ArrayList();
            Map<String,Object> scoreMap = new HashMap<>();
            int sum = 0;
            for (int i=0; i<determineFactorsList.size();i++ ){
                sum = sum + determineFactorsList.get(i).getScore();

                if (determineFactorsList.get(i).getCategoryId() ==1){
                    scoreMap.put("accidentconsequences",determineFactorsList.get(i).getScore());
                    break;
                }
                // System.out.print(determineFactorsList.get(i).getScore());
                list.add(determineFactorsList.get(i).getScore());
            }
            System.out.println(sum);
            return ResultEntity.successWithData(list);
        }catch(Exception e){
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }
   }
   // 获取所有的分数
    @RequestMapping("/get/all/score")
    public ResultEntity<Object> getAllScore(){
        List<FactorScore> scoreList = concreteEvaluationService.getAllScore();
        List list = new ArrayList();
        /*for(int i=0;i<scoreList.size();i++){

        }*/
        System.out.println(scoreList);
        return ResultEntity.successWithData(scoreList);
    }
}
