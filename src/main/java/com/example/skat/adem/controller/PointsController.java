package com.example.skat.adem.controller;


import com.example.skat.adem.model.PointModel;
import com.example.skat.adem.model.ResponseModel;
import com.example.skat.adem.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/points")
public class PointsController {

    private final PointService pointService;
    private final ResponseModel responseModel;

    @Autowired
    public PointsController(PointService pointService, ResponseModel responseModel) {
        this.pointService = pointService;
        this.responseModel = responseModel;
    }

    @GetMapping("/randomPoints")
    private PointModel getRandomPointsCombination() {
        return pointService.getRandomPointsCombination();
    }

    @PostMapping("/randomPoints")
    public ResponseModel postCalculatedPoints(@RequestBody List<Integer> calculatedPointsList) {

        boolean validPoints = pointService.validatePoints(calculatedPointsList);

        if (validPoints) {
            int sum = pointService.validateCalculatedPoints(calculatedPointsList);
            responseModel.setSuccess("Success");
            responseModel.setSum(sum);
        } else {
            responseModel.setSuccess("Point set is not valid");
            responseModel.setSum(-1);
        }

        return responseModel;
    }
}
