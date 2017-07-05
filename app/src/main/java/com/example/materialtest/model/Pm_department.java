package com.example.materialtest.model;

import java.io.Serializable;

/**
 * Created by xzs on 2017/6/5.
 */

public class Pm_department implements Serializable {
    private int dep_id; //部门id
    private String dep_name; //部门名称


    public String getDep_name() {
        return dep_name;
    }
    public void setDep_name(String dep_name) {
        this.dep_name = dep_name;
    }
    public int getDep_id() {
        return dep_id;
    }
    public void setDep_id(int dep_id) {
        this.dep_id = dep_id;
    }


}
