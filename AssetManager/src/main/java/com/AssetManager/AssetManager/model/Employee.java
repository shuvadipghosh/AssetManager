package com.AssetManager.AssetManager.model;

import com.mongodb.lang.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document
public class Employee {
    @Id
    private String ID;
    @NonNull
    private String employeeCode;
    private String name;
    private List<Asset> assets = new ArrayList<>();
    public Employee(){

    }
    public Employee(String ID, @NonNull String employeeCode, String name, List<Asset> assets,String aDate,String rDate) {
        this.ID = ID;
        this.employeeCode = employeeCode;
        this.name = name;
        this.assets = assets;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    @NonNull
    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(@NonNull String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Asset> getAssets() {
        return assets;
    }

    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }
}
