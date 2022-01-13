package com.example.skat.adem.service;

import com.example.skat.adem.model.ResponseModel;
import com.example.skat.adem.repository.ResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseService {
    private final ResponseRepository responseRepository;

    @Autowired
    public ResponseService(ResponseRepository responseRepository) {
        this.responseRepository = responseRepository;
    }

    public List<ResponseModel> getResponses() {
        return responseRepository.findAll();
    }
}
