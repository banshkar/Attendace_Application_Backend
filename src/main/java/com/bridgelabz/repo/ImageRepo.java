package com.bridgelabz.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.model.FileModel;

public interface ImageRepo  extends JpaRepository<FileModel, Integer>{

}
