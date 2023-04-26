package com.AssetManager.AssetManager.Service;
import com.AssetManager.AssetManager.Repo.EmployeeRepository;
import com.AssetManager.AssetManager.model.Asset;
import com.AssetManager.AssetManager.model.Employee;
import com.AssetManager.AssetManager.model.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {
    @Autowired
    private final EmployeeRepository employeeRepository;
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    public Employee createEmployee(Employee emp){
        emp.setID(UUID.randomUUID().toString());
        return employeeRepository.insert(emp);
    }
    public Employee findEmployee(String ID){
        List<Employee>employees = new ArrayList<>();
        employees = employeeRepository.findAll();
        for(Employee employee : employees){
            if(employee.getEmployeeCode().equals(ID)){
                return  employee;
            }
        }
        return null;
    }
    public Employee updateEmployeeAsset(Asset asset){
        String empCode = asset.getEmp();
        Employee employee = findEmployee(empCode);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        if(employee!=null) {
            List<Asset>presentAsset = employee.getAssets();
            presentAsset.add(asset);
            employee.setAssets(presentAsset);
        }
        return  employeeRepository.save(employee);
    }
    public void  deleteEmployeeAsset(Asset asset){
        String empCode = asset.getEmp();
        Employee employee = findEmployee(empCode);
        List<Asset> assets = employee.getAssets();
        for(Asset a : assets){
            if(a.getId().equals(asset.getId())){
                assets.remove(a);
                break;
            }
        }
        employee.setAssets(assets);
        employeeRepository.save(employee);
    }
    public List<Employee> getAllEmployee(){
        return  employeeRepository.findAll();
    }
}
