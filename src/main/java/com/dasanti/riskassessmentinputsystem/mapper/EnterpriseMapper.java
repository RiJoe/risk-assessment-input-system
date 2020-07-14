package com.dasanti.riskassessmentinputsystem.mapper;

import com.dasanti.riskassessmentinputsystem.entity.Enterprise;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestParam;

@Mapper
public interface EnterpriseMapper {
    Enterprise getEnterpriseById(@RequestParam Integer id);

    Integer saveEnterprise(Enterprise enterprise);

    Integer getEnterpriseId(String companyName);
}
