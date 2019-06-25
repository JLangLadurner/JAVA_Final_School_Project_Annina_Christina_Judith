package com.school.project.FX_Project;

public class SchoolClass {

    private String className;

    public SchoolClass(String className) {
        this.className = className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    @Override
    public String toString() {
        return className;
    }
}

