package com.tindev.tindevapi.repository;

import com.tindev.tindevapi.entities.PersonInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonInfoRepository extends JpaRepository<PersonInfoEntity, Integer> {




}
