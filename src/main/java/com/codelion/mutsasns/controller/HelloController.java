package com.codelion.mutsasns.controller;

import com.codelion.mutsasns.service.AlgorithmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/hello")
public class HelloController {

    private AlgorithmService algorithmService;
    @GetMapping("")
    public String helloIndex() {
        return "장서윤";
    }

    @GetMapping("/{num}")
    public String sumOfDigit(@PathVariable("num") Integer num) {
        algorithmService.sumOfDigit(num);
        return "";
    }
}
