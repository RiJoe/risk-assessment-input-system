package com.dasanti.riskassessmentinputsystem.mapper;

import com.dasanti.riskassessmentinputsystem.entity.DetermineFactors;
import com.dasanti.riskassessmentinputsystem.entity.FactorScore;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ConcreteEvaluationMapper {
    void saveDetermineFactors(DetermineFactors determineFactors);

    Integer getScoreByDetermineFactor(String scoreName);

    String getPhotoById(Integer enterpriseId, Integer influenceFactorId, String determineFactor);

    List<DetermineFactors> getDetermineFactorListById(Integer enterpriseId);

    List<FactorScore> getAllScore();
}
