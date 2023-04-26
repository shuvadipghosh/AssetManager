package com.AssetManager.AssetManager.Service;

import com.AssetManager.AssetManager.Repo.HistoryRepo;
import com.AssetManager.AssetManager.model.Asset;
import com.AssetManager.AssetManager.model.History;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class HistoryService {
    private  final HistoryRepo repo;

    public HistoryService(HistoryRepo repo) {
        this.repo = repo;
    }
    public History create(Asset asset){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        History history = new History();
        history.setId(UUID.randomUUID().toString());
        history.setAssetID(asset.getId());
        history.setEmpID(asset.getEmp());
        repo.insert(history);
        return  history;
    }
    public List<History> getAssetHistory(String assetId){
        List<History> histories= repo.findAll();
        List<History> currentAssetHistory = new ArrayList<>();
        for(History  history : histories){
            if(history.getAssetID().equals(assetId)){
                currentAssetHistory.add(history);
            }
        }
        return currentAssetHistory;
    }
}
