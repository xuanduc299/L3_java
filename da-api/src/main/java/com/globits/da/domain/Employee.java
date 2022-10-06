package com.globits.da.domain;

import com.globits.core.domain.BaseObject;

import javax.persistence.*;

@Entity
@Table(name = "tb_employee")
public class Employee extends BaseObject {
    @Column
    private String code;
    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String phone;
    @Column
    private String age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ep_province_id",referencedColumnName = "id",nullable = false)
    private Province province;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ep_district_id",referencedColumnName = "id",nullable = false)
    private District district;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ep_village_id",referencedColumnName = "id",nullable = false)
    private Village village;

    public Employee() {
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

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Village getVillage() {
        return village;
    }

    public void setVillage(Village village) {
        this.village = village;
    }
}
