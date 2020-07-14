package com.dasanti.riskassessmentinputsystem.service.serviceImpl;

import com.dasanti.riskassessmentinputsystem.entity.Enterprise;
import com.dasanti.riskassessmentinputsystem.mapper.EnterpriseMapper;
import com.dasanti.riskassessmentinputsystem.service.EnterpriseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class EnterpriseServiceImpl implements EnterpriseService {
    @Resource
    private EnterpriseMapper enterpriseMapper;

    @Override
    public Enterprise getEnterpriseById(Integer id) {
        return enterpriseMapper.getEnterpriseById(id);
    }

    @Override
    public Integer saveEnterprise(Enterprise enterprise) {

        enterpriseMapper.saveEnterprise(enterprise);

        return enterprise.getId();
    }

    @Override
    public Integer getEnterpriseId(String companyName) {
         return enterpriseMapper.getEnterpriseId(companyName);
    }
}
