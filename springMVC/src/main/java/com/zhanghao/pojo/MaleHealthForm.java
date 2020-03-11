package com.zhanghao.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(value = { "handler" })
public class MaleHealthForm extends HealthForm implements Serializable {
    private String prostate;

    public String getProstate() {
        return prostate;
    }

    public void setProstate(String prostate) {
        this.prostate = prostate;
    }

    @Override
    public String toString() {
        return super.toString() + "MaleHealthForm{" +
                "prostate='" + prostate + '\'' +
                '}';
    }
}
