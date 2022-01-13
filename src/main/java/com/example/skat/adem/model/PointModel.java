package com.example.skat.adem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;

import java.util.List;

@Builder
public class PointModel {
    public List<Object> points;
    @JsonIgnore
    public int sum;

}
