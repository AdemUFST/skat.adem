package com.example.skat.adem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table
@Component
public class ResponseModel {

    @Id
    @SequenceGenerator(
            name = "response_sequence",
            sequenceName = "response_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "response_sequence"
    )
    @JsonIgnore
    private Long id;

    private String success;
    private Integer sum;

    public ResponseModel(String success, Integer sum) {
        this.success = success;
        this.sum = sum;
    }

    public ResponseModel() {

    }
}
