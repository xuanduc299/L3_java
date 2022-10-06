package com.globits.da.dto;

import com.globits.core.dto.BaseObjectDto;
import com.globits.da.domain.Employee;

import java.util.UUID;

public class EmployeeDto extends BaseObjectDto {
    private String code;
    private String name;
    private String email;
    private String phone;
    private String age;
    private UUID provinceId;
    private UUID districtId;
    private UUID villageId;

    public EmployeeDto() {
    }

    public EmployeeDto(Employee entity) {
        if (entity != null){
            this.setId(entity.getId());
            this.setCreateDate(entity.getCreateDate());
            this.setCreatedBy(entity.getCreatedBy());
            this.setModifyDate(entity.getModifyDate());
            this.setCreatedBy(entity.getCreatedBy());
            this.setCode(entity.getCode());
            this.setName(entity.getName());
            this.setEmail(entity.getEmail());
            this.setPhone(entity.getPhone());
            this.setAge(entity.getAge());
            this.setProvinceId(entity.getProvince().getId());
            this.setDistrictId(entity.getDistrict().getId());
            this.setVillageId(entity.getVillage().getId());
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public UUID getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(UUID provinceId) {
        this.provinceId = provinceId;
    }

    public UUID getDistrictId() {
        return districtId;
    }

    public void setDistrictId(UUID districtId) {
        this.districtId = districtId;
    }

    public UUID getVillageId() {
        return villageId;
    }

    public void setVillageId(UUID villageId) {
        this.villageId = villageId;
    }
}
