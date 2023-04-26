package com.AssetManager.AssetManager.Repo;

import com.AssetManager.AssetManager.model.History;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HistoryRepo extends MongoRepository<History,String> {
}
