package com.devhub.use.cvrceapplication.models;

public class MentorModel {
    String empid,name;
   public MentorModel(String empid,String name){
        this.empid = empid;
        this.name = name;

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
