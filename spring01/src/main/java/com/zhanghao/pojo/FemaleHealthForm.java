package com.zhanghao.pojo;

public class FemaleHealthForm extends HealthForm {
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
