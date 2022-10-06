package com.globits.da.dto;

import com.globits.core.dto.BaseObjectDto;
import com.globits.da.domain.District;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

import java.util.List;
import java.util.UUID;

@JsonPropertyOrder({"list", "provinceDto"})
public class DistrictDto extends BaseObjectDto {
    private String code;
    private String name;
    private Float area;

    private ProvinceDto provinceDto;

    private List<VillageDto> villageDto;

    public DistrictDto() {
    }
    public DistrictDto(UUID id) {
        this.setId(id);
    }

    public DistrictDto(District entity){
        if(entity!= null){
            this.setId(entity.getId());
            this.setCreateDate(entity.getCreateDate());
            this.setModifiedBy(entity.getModifiedBy());
            this.setCreatedBy(entity.getCreatedBy());
            this.setModifyDate(entity.getModifyDate());
            this.setCode(entity.getCode());
            this.setName(entity.getName());
            this.setArea(entity.getArea());
            this.setProvinceDto(new ProvinceDto(entity.getProvince()));
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

    public ProvinceDto getProvinceDto() {
        return provinceDto;
    }

    public void setProvinceDto(ProvinceDto provinceDto) {
        this.provinceDto = provinceDto;
    }

    public List<VillageDto> getVillageDto() {
        return villageDto;
    }

    public void setVillageDto(List<VillageDto> villageDto) {
        this.villageDto = villageDto;
    }
}
