package com.dasanti.riskassessmentinputsystem.service;

import com.dasanti.riskassessmentinputsystem.entity.Enterprise;

public interface EnterpriseService {

    Enterprise getEnterpriseById(Integer id);

    Integer saveEnterprise(Enterprise enterprise);

    Integer getEnterpriseId(String companyName);
}
