package com.globits.da.domain;

import com.globits.core.domain.BaseObject;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tb_certificate")
public class Certificate extends BaseObject {
    //Van bang
    @Column
    private String code;
    @Column
    private String name;
    @Column
    private LocalDate EffectFrom;
    @Column
    private LocalDate EffectTo;

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
