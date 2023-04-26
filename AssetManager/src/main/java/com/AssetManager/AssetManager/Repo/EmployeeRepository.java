package com.AssetManager.AssetManager.Repo;

import com.AssetManager.AssetManager.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepository extends MongoRepository<Employee,String> {
}
