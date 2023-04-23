package com.bridgelabz.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.model.DataStore;

public interface DataStoreRepo extends JpaRepository<DataStore, Integer> {

}
