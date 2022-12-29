package com.codelion.mutsasns.service;

import org.springframework.stereotype.Service;

@Service
public class AlgorithmService {
    public Integer sumOfDigit(Integer pathNum) {
        int sum = 0;
        while (pathNum > 0) {
            sum += (pathNum % 10);
            pathNum = (pathNum / 10);
        }
        return sum;
    }

}
