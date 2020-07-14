package com.dasanti.riskassessmentinputsystem.service.serviceImpl;

import com.dasanti.riskassessmentinputsystem.entity.DetermineFactors;
import com.dasanti.riskassessmentinputsystem.entity.FactorScore;
import com.dasanti.riskassessmentinputsystem.mapper.ConcreteEvaluationMapper;
import com.dasanti.riskassessmentinputsystem.service.ConcreteEvaluationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ConcreteEvaluationServiceImpl implements ConcreteEvaluationService {
    @Resource
    private ConcreteEvaluationMapper concreteEvaluationMapper;


    @Override
    public void saveDetermineFactors(DetermineFactors determineFactors) {
        concreteEvaluationMapper.saveDetermineFactors(determineFactors);
    }

    @Override
    public Integer getScoreByDetermineFactor(String scoreName) {
        return concreteEvaluationMapper.getScoreByDetermineFactor(scoreName);
    }

    @Override
    public String getPhotoById(Integer enterpriseId, Integer influenceFactorId, String determineFactor) {
        return concreteEvaluationMapper.getPhotoById(enterpriseId,influenceFactorId,determineFactor);
    }

    @Override
    public List<DetermineFactors> getDetermineFactorsListById(Integer enterpriseId) {
        return concreteEvaluationMapper.getDetermineFactorListById(enterpriseId);
    }

    @Override
    public List<FactorScore> getAllScore() {
        return concreteEvaluationMapper.getAllScore();
    }
}
