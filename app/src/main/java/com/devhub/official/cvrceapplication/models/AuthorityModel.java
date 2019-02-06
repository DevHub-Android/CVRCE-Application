package com.devhub.official.cvrceapplication.models;

public class AuthorityModel {
    String empid;
    String position ;
    String domain;
    int priority;
    String first_name ;

    public AuthorityModel(String empid, String position, String domain, int priority, String first_name) {
        this.empid = empid;
        this.position = position;
        this.domain = domain;
        this.priority = priority;
        this.first_name = first_name;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
}
