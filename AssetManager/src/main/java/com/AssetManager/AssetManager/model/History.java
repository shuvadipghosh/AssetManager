package com.AssetManager.AssetManager.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class History {
    @Id
    String Id;
    String assetID;
    String empID;

    public History(){}
    public History(String Id,String assetID, String empID,String aDate,String rDate) {
        this.Id = Id;
        this.assetID = assetID;
        this.empID = empID;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getAssetID() {
        return assetID;
    }

    public void setAssetID(String assetID) {
        this.assetID = assetID;
    }

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }
}
