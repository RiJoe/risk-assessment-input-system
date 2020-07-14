package com.dasanti.riskassessmentinputsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetermineFactors {
    private Integer id;
    private Integer enterpriseId;
    private Integer categoryId;
    private Integer influenceFactorId;
    private String determineFactor;
    private Integer score;
    private String photo;
    private String remark;
}
