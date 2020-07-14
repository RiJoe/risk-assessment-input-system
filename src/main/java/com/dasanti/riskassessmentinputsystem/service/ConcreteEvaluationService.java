package com.dasanti.riskassessmentinputsystem.service;

import com.dasanti.riskassessmentinputsystem.entity.DetermineFactors;
import com.dasanti.riskassessmentinputsystem.entity.FactorScore;

import java.util.List;

public interface ConcreteEvaluationService {


    void saveDetermineFactors(DetermineFactors determineFactors);

    Integer getScoreByDetermineFactor(String scoreName);

    String getPhotoById(Integer enterpriseId, Integer influenceFactorId, String determineFactor);

    List<DetermineFactors> getDetermineFactorsListById(Integer enterpriseId);

    List<FactorScore> getAllScore();
}
