package com.dasanti.riskassessmentinputsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FactorScore {
    private String name;
    private Integer score;
}
