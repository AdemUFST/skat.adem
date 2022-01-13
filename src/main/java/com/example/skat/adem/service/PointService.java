package com.example.skat.adem.service;


import com.example.skat.adem.model.PointModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Service
public class PointService {

    private final int SINGLE_THROW_MAX_POINTS = 10;

    public PointModel getRandomPointsCombination() {
        List<Object> listOfPoints = new ArrayList<>();

        //Generate random number of pairs - always an even number as the numbers always come in pairs of two
        // e.g: 2 would result in the pair 4,6, and 4 would result in the pair 5,3, 3,7
        int randomIntBetweenOneAndTen = getRandomIntBetween(1, SINGLE_THROW_MAX_POINTS);
        int numberOfPairs = randomIntBetweenOneAndTen % 2 == 0 ? randomIntBetweenOneAndTen : randomIntBetweenOneAndTen + 1;
        for (int i = 0; i < numberOfPairs; i++) {
            int firstThrow = getRandomIntBetween(0, SINGLE_THROW_MAX_POINTS+1);
            int lastThrow = 0; //assume first throw is 10
            if (firstThrow == SINGLE_THROW_MAX_POINTS) {
                listOfPoints.addAll(Arrays.asList(firstThrow, lastThrow)); // [10, 0]
            } else {
                lastThrow = getRandomIntBetween(0, SINGLE_THROW_MAX_POINTS - firstThrow + 1);
                listOfPoints.add(Stream.of(firstThrow, lastThrow)); // e.g. [8, 1]
            }
        }
        return PointModel.builder().points(listOfPoints).build();
    }

    public int validateCalculatedPoints(List<Integer> calculatedPointsList) {
        int score = 0;

        for (int i = 0; i < calculatedPointsList.size(); i += 2) {

            int currentFrame_1 = calculatedPointsList.get(i);
            int currentFrame_2 = (i < (calculatedPointsList.size() - 1) ? calculatedPointsList.get(i + 1) : 0);

            int nextFrame_1 = (i < calculatedPointsList.size() - 2 ? calculatedPointsList.get(i + 2) : 0);
            int nextFrame_2 = (i < calculatedPointsList.size() - 3 ? calculatedPointsList.get(i + 3) : 0);

            int nextAfterNextFrame = (i < (calculatedPointsList.size() - 4) ? calculatedPointsList.get(i + 4) : 0);

            //open = current frame added
            //spare = 10 + next knocked down
            //strike = 10 + next knocked down + the following
            if (isStrike(currentFrame_1)) {
                score += 10;
                if (isStrike(nextFrame_1)) {
                    score += 10;
                    //If strike again (last turn)
                    if (isStrike(nextAfterNextFrame)) {
                        score += 10;
                    } else {
                        score += nextAfterNextFrame;
                    }
                } else {
                    score += nextFrame_1 + nextFrame_2;
                }
                //if it wasn't a strike but a spare
            } else if (isSpare(currentFrame_1, currentFrame_2)) {
                score += 10 + nextFrame_1;
            } else { //if neither
                score += currentFrame_1 + currentFrame_2;
            }
        }
        return score;
    }

    private int getRandomIntBetween(int lower, int upper) {
        return new Random().nextInt(upper) + lower;
    }

    private boolean isStrike(int point) {
        return point == 10;
    }

    private boolean isSpare(int currentFrame_1, int currentFrame_2) {
        return currentFrame_1 + currentFrame_2 == 10;
    }

    public boolean validatePoints(List<Integer> list) {
        boolean isValid = true;
        for (int i = 0; i < list.size(); i += 2) {
            int sum = list.get(i) + list.get(i + 1);
            if (sum > 10) {
                return false;
            }
        }
        return isValid;
    }
}
