package com.example.skat.adem.repository;

import com.example.skat.adem.model.ResponseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseRepository extends JpaRepository<ResponseModel, Long> {
}
