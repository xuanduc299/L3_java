package com.globits.da.dto;

import com.globits.core.dto.BaseObjectDto;
import com.globits.da.domain.Village;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

import java.util.UUID;
@JsonPropertyOrder({"list", "districtDto"})
public class VillageDto extends BaseObjectDto {
    private String code;
    private String name;
    private Float area;

    private DistrictDto districtDto;

    public VillageDto() {
    }
    public VillageDto(UUID id) {
        this.setId(id);
    }

    public VillageDto(Village entity){
        if(entity!= null){
            this.setId(entity.getId());
            this.setCreateDate(entity.getCreateDate());
            this.setModifiedBy(entity.getModifiedBy());
            this.setCreatedBy(entity.getCreatedBy());
            this.setModifyDate(entity.getModifyDate());
            this.setCode(entity.getCode());
            this.setName(entity.getName());
            this.setArea(entity.getArea());
            this.setDistrictDto(new DistrictDto(entity.getDistrict()));
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

    public DistrictDto getDistrictDto() {
        return districtDto;
    }

    public void setDistrictDto(DistrictDto districtDto) {
        this.districtDto = districtDto;
    }
}
