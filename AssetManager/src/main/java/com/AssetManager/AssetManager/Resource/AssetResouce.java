package com.AssetManager.AssetManager.Resource;

import com.AssetManager.AssetManager.Service.AssetService;
import com.AssetManager.AssetManager.Service.EmployeeService;
import com.AssetManager.AssetManager.Service.HistoryService;
import com.AssetManager.AssetManager.model.Asset;
import com.AssetManager.AssetManager.model.Employee;
import com.AssetManager.AssetManager.model.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Asset")
public class AssetResouce {
    @Autowired
    private final AssetService service;
    @Autowired
    private final EmployeeService employeeServiceervice;
    @Autowired
    private final HistoryService historyService;
    public  AssetResouce(AssetService service,EmployeeService service1, HistoryService service2){
         this.service=service;
         this.employeeServiceervice = service1;
         this.historyService=service2;
    }
    @GetMapping("/all")
    public ResponseEntity<List<Asset>> onGetAssets(){
        List<Asset> assets = service.findAllAsset();
        return new ResponseEntity<>(assets, HttpStatus.OK);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<Asset> getAssetBasedonID(@PathVariable("id") String id){
        return new ResponseEntity<>(service.findByID(id),HttpStatus.OK);
    }
    @PostMapping("/addAsset")
    public ResponseEntity<Asset> createAsset(@RequestBody Asset asset){
         Asset newAsset = service.create(asset);
         employeeServiceervice.updateEmployeeAsset(newAsset);
         historyService.create(asset);
         return  new ResponseEntity<>(newAsset,HttpStatus.OK);
    }
    @DeleteMapping("/deleteAsset/{id}")
    public  ResponseEntity<Asset>deleteAsset(@PathVariable("id") String id){
        Asset asset = service.findByID(id);
        if(asset!=null) {
            if(!asset.getEmp().isEmpty()) {
                employeeServiceervice.deleteEmployeeAsset(asset);
            }
            service.deleteByID(id);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/findAsset/{name}")
    public ResponseEntity<List<Asset>>getAssetName(@PathVariable("name") String name){
        return new ResponseEntity<>(service.findAssetBasedOnName(name),HttpStatus.OK);
    }
    @PutMapping("/updateAsset/{id}/{empID}")
    public  ResponseEntity<Asset>updateAssetStatus(@PathVariable("id") String id, @PathVariable("empID") String emp){
        Asset asset = service.updateAssetStatus(id, emp);
            employeeServiceervice.updateEmployeeAsset(asset);
            historyService.create(asset);
        return new ResponseEntity<>(asset,HttpStatus.OK);
    }
    @PutMapping("/updateAssetEmployee/{id}/{empID}")
    public  ResponseEntity<Asset>updateAssetFromEmployeeByStatus(@PathVariable("id") String id, @PathVariable("empID") String emp){
        Asset asset = service.updateAssetStatus(id, emp);
        employeeServiceervice.deleteEmployeeAsset(asset);
        Asset newAsset = service.updateAssetStatusToFalse(asset);
        return new ResponseEntity<>(newAsset,HttpStatus.OK);
    }
    @GetMapping("/assetHistory/{id}")
    public ResponseEntity<List<Employee>>getHistory(@PathVariable("id") String assetID){
        List<History> currentAssetHistory = historyService.getAssetHistory(assetID);
        List<Employee>employees = new ArrayList<>();
        for(History history : currentAssetHistory){
            employees.add(employeeServiceervice.findEmployee(history.getEmpID()));
        }
        return  new ResponseEntity<>(employees,HttpStatus.OK);
    }
}
