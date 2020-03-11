package com.zhanghao.service.impl;

import com.zhanghao.service.Juice;

public class JuiceMake2 implements Juice {
    private String name;
    private String cup;
    private String suger;

    public JuiceMake2(String name, String cup, String suger) {
        this.name = name;
        this.cup = cup;
        this.suger = suger;
    }

    public void makeJuice() {
        System.out.println("要" + cup +name+","+suger);
    }
}
