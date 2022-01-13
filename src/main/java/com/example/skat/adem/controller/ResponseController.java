package com.example.skat.adem.controller;

import com.example.skat.adem.model.ResponseModel;
import com.example.skat.adem.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/response")
public class ResponseController {

    private final ResponseService responseService;

    @Autowired
    public ResponseController(ResponseService responseService) {
        this.responseService = responseService;
    }

    @GetMapping("/all")
    private List<ResponseModel> getAllResponses(){
        return responseService.getResponses();
    }
}
