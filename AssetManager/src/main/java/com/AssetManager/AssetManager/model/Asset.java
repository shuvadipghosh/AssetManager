package com.AssetManager.AssetManager.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
@Document("asset")
public class Asset implements Serializable {
    @Id
    private String id;
    private String assetName;
    private String assetDescription;
    private String emp;
    private boolean availability;

    public Asset() {
    }

    public Asset(String ID, String assetName, String assetDescription, boolean availability, String emp) {
        this.id = ID;
        this.assetName = assetName;
        this.assetDescription = assetDescription;
        this.availability = availability;
        this.emp = emp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getAssetDescription() {
        return assetDescription;
    }

    public void setAssetDescription(String assetDescription) {
        this.assetDescription = assetDescription;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public String getEmp() {
        return emp;
    }

    public void setEmp(String emp) {
        this.emp = emp;
    }
}
