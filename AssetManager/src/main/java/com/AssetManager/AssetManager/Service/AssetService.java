package com.AssetManager.AssetManager.Service;

import com.AssetManager.AssetManager.Repo.AssetRepository;
import com.AssetManager.AssetManager.model.Asset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AssetService {
    @Autowired
    private final AssetRepository repo;
    public AssetService(AssetRepository repo){
        this.repo=repo;
    }
    public Asset create(Asset asset){
        asset.setId(UUID.randomUUID().toString());
        asset.setAvailability(false);
        return repo.insert(asset);
    }
    public List<Asset> findAllAsset(){
        return repo.findAll();
    }
    public Asset findByID(String ID){
        List<Asset>assets = repo.findAll();
        for(Asset asset : assets){
            if(asset.getId().equals(ID))
            {
                return asset;
            }
        }
        return null;
    }
    public void deleteByID(String ID){
        repo.deleteById(ID);
    }
    public List<Asset>findAssetBasedOnName(String name){
        List<Asset>assets = repo.findAll();
        List<Asset>newAsset = new ArrayList<>();
        for(Asset asset : assets){
            if(asset.getAssetName().equals(name))
                newAsset.add(asset);
        }
        return newAsset;
    }

    public Asset updateAssetStatus(String ID, String emp){
        Asset asset = findByID(ID);
        asset.setEmp(emp);
        if(asset.isAvailability() == true){
            asset.setAvailability(false);
        }
        else
            asset.setAvailability(true);
        return  repo.save(asset);
    }
    public Asset updateAssetStatusToFalse(Asset asset){
        asset.setEmp("");
        asset.setAvailability(true);
        return  repo.save(asset);
    }
}
