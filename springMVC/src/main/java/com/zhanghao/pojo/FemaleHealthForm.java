package com.zhanghao.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
@JsonIgnoreProperties(value = { "handler" })
public class FemaleHealthForm extends HealthForm implements Serializable {
    private String uterus;

    public String getUterus() {
        return uterus;
    }

    public void setUterus(String uterus) {
        this.uterus = uterus;
    }

    @Override
    public String toString() {
        return super.toString()+"FemaleHealthForm{" +
                "uterus='" + uterus + '\'' +
                '}';
    }
}
