package com.AssetManager.AssetManager.Resource;

import com.AssetManager.AssetManager.Service.EmployeeService;
import com.AssetManager.AssetManager.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Employee")
public class EmployeeResource {
    @Autowired
    private final EmployeeService service;

    public EmployeeResource(EmployeeService service) {
        this.service = service;
    }
    @GetMapping("/all")
    public ResponseEntity<List<Employee>>getAllEmployee(){
        List<Employee> employees = service.getAllEmployee();
        return  new ResponseEntity<>(employees, HttpStatus.OK);
    }
    @PostMapping("/addEmployee")
    public ResponseEntity<Employee>addEmployee(@RequestBody Employee employee){
        Employee newEmployee = service.createEmployee(employee);
        return new ResponseEntity<>(newEmployee,HttpStatus.CREATED);
    }

}
