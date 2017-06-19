package com.example.materialtest.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xzs on 2017/6/7.
 */

public class Test implements Serializable {
    private String ic_task_id;
    private String dep_name;
    private String icDepartment;
    private List<InterchangeNotice> icnLst;
    private List<Users> userLst;
    private String depType;

    public String getDepType() {
        return depType;
    }

    public void setDepType(String depType) {
        this.depType = depType;
    }

    public String getIc_task_id() {
        return ic_task_id;
    }
    public void setIc_task_id(String ic_task_id) {
        this.ic_task_id = ic_task_id;
    }
    public String getDep_name() {
        return dep_name;
    }
    public void setDep_name(String dep_name) {
        this.dep_name = dep_name;
    }
    public String getIcDepartment() {
        return icDepartment;
    }
    public void setIcDepartment(String icDepartment) {
        this.icDepartment = icDepartment;
    }
    public List<InterchangeNotice> getIcnLst() {
        return icnLst;
    }
    public void setIcnLst(List<InterchangeNotice> icnLst) {
        this.icnLst = icnLst;
    }
    public List<Users> getUserLst() {
        return userLst;
    }
    public void setUserLst(List<Users> userLst) {
        this.userLst = userLst;
    }


}
