package com.zhanghao.pojo;

public class MaleHealthForm extends HealthForm {
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
