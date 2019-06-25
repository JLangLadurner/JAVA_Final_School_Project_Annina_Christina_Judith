package com.school.project.FX_Project;

public class Parent {
    private int parentID;
    private String parFirstName;
    private String parLastName;
    private String parEmail;
    private String parPassword;

    public Parent(int parentID, String parFirstName, String parLastName, String parEmail, String parPassword) {
        this.parentID = parentID;
        this.parFirstName = parFirstName;
        this.parLastName = parLastName;
        this.parEmail = parEmail;
        this.parPassword = parPassword;
    }

    public int getParentID() {
        return parentID;
    }

    public void setParentID(int parentID) {
        this.parentID = parentID;
    }

    public String getParFirstName() {
        return parFirstName;
    }

    public void setParFirstName(String parFirstName) {
        this.parFirstName = parFirstName;
    }

    public String getParLastName() {
        return parLastName;
    }

    public void setParLastName(String parLastName) {
        this.parLastName = parLastName;
    }

    public String getParEmail() {
        return parEmail;
    }

    public void setParEmail(String parEmail) {
        this.parEmail = parEmail;
    }

    public String getParPassword() {
        return parPassword;
    }

    public void setParPassword(String parPassword) {
        this.parPassword = parPassword;
    }
}
