package com.globits.da.dto;

import com.globits.core.dto.BaseObjectDto;
import com.globits.da.domain.Province;

import java.util.List;
import java.util.UUID;

public class ProvinceDto extends BaseObjectDto {
    private String code;
    private String name;
    private Float area;

    private List<DistrictDto> districtDto;

    public ProvinceDto() {
    }

    public ProvinceDto(UUID id) {
        this.setId(id);
    }

    public ProvinceDto(Province entity) {
        if (entity != null) {
            this.setId(entity.getId());
            this.setCreateDate(entity.getCreateDate());
            this.setModifiedBy(entity.getModifiedBy());
            this.setCreatedBy(entity.getCreatedBy());
            this.setModifyDate(entity.getModifyDate());
            this.setCode(entity.getCode());
            this.setName(entity.getName());
            this.setArea(entity.getArea());
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

    public Float getArea() {
        return area;
    }

    public void setArea(Float area) {
        this.area = area;
    }

    public List<DistrictDto> getDistrictDto() {
        return districtDto;
    }

    public void setDistrictDto(List<DistrictDto> districtDto) {
        this.districtDto = districtDto;
    }
}
