package com.AssetManager.AssetManager.Repo;

import com.AssetManager.AssetManager.model.Asset;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface AssetRepository extends MongoRepository<Asset,String> {

}
