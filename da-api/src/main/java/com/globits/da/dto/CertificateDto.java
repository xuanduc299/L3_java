package com.globits.da.dto;

import com.globits.core.dto.BaseObjectDto;
import com.globits.da.domain.Certificate;

import java.time.LocalDate;

public class CertificateDto extends BaseObjectDto {
    private String code;
    private String name;
    private LocalDate EffectFrom;

    private LocalDate EffectTo;

    public CertificateDto(){}

    public CertificateDto(Certificate entity){
        if(entity !=null){
            this.setId(entity.getId());
            this.setCreateDate(entity.getCreateDate());
            this.setModifiedBy(entity.getModifiedBy());
            this.setCreatedBy(entity.getCreatedBy());
            this.setModifyDate(entity.getModifyDate());
            this.setCode(entity.getCode());
            this.setName(entity.getName());
            this.setEffectFrom(entity.getEffectFrom());
            this.setEffectTo(entity.getEffectTo());
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getEffectFrom() {
        return EffectFrom;
    }

    public void setEffectFrom(LocalDate effectFrom) {
        EffectFrom = effectFrom;
    }

    public LocalDate getEffectTo() {
        return EffectTo;
    }

    public void setEffectTo(LocalDate effectTo) {
        EffectTo = effectTo;
    }
}
