package com.example.skat.adem;

import com.example.skat.adem.service.PointService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.List;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SimpleTests {
    @LocalServerPort
    private int port = 8081;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private final PointService pointService;

    @Autowired
    public SimpleTests(PointService pointService) {
        this.pointService = pointService;
    }

    @Test
    public void getPointsFromEndpoint() {
        String result = restTemplate.getForObject("http://localhost:" + port + "/points/randomPoints", String.class);
        //e.g. "points":[[2,3]"
        String validPattern = "\"points\":\\[\\[\\d,\\d]";
        Pattern pattern = Pattern.compile(validPattern);
        boolean matcher = pattern.matcher(result).find();

        assertTrue(matcher, "valid points not found");
    }

    @Test
    public void validateValidPoints() {
        List<Integer> validPoints = List.of(10, 0, 10, 0, 3, 7, 7, 3, 10, 0, 5, 5, 0, 0, 0, 9, 10, 0);
        boolean isValid = pointService.validatePoints(validPoints);

        assertTrue(isValid, "points are not valid");
    }

    @Test
    public void expectErrorInValidatingPoints() {
        List<Integer> validPoints = List.of(7, 7);
        boolean isValid = pointService.validatePoints(validPoints);
        assertFalse(isValid, "points are valid");
    }

    @Test
    public void validateCalculatedPoints() {
        List<Integer> points = List.of(10, 0, 10, 0, 10, 0, 10, 0);
        int expectedSum = pointService.validateCalculatedPoints(points);

        assertEquals(90, expectedSum, "Error in calculation. Expected to be equal");
    }

    @Test
    public void validateCalculatedPointsError() {
        List<Integer> points = List.of(10, 0, 10, 0, 10, 0, 0, 10); //score is 80
        int expectedSum = pointService.validateCalculatedPoints(points);

        assertNotEquals(90, expectedSum, "Error in calculation. Expected to be not equal");
    }
}
