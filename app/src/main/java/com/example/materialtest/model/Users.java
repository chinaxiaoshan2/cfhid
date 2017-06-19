package com.example.materialtest.model;

import java.io.Serializable;

/**
 * Created by xzs on 2017/6/5.
 */

public class Users implements Serializable {
    private String user_id; //id
    private String user_name; //姓名
    private int dep_id; //部门id
    private int user_Role;//用户角色

    private int user_role;//用户角色

    //---------------显示试图--------------------
    private String department;
    private String dep_name; //部门名称
    private String user_password; //用户密码


    public String getUser_name() {
        return user_name;
    }
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
    public int getDep_id() {
        return dep_id;
    }
    public void setDep_id(int dep_id) {
        this.dep_id = dep_id;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public int getUser_Role() {
        return user_Role;
    }
    public void setUser_Role(int user_Role) {
        this.user_Role = user_Role;
    }
    public String getUser_password() {
        return user_password;
    }
    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }
    public String getDep_name() {
        return dep_name;
    }
    public void setDep_name(String dep_name) {
        this.dep_name = dep_name;
    }
    public int getUser_role() {
        return user_role;
    }
    public void setUser_role(int user_role) {
        this.user_role = user_role;
    }
    public String getUser_id() {
        return user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }


}
