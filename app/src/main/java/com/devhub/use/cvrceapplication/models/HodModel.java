package com.devhub.use.cvrceapplication.models;

/**
 * Created by USER on 20-10-2018.
 */

public class HodModel {
    String empid,name,department;
    public HodModel(String empid,String name,String department){
        this.empid = empid;
        this.name = name;
        this.department=department;

    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
