package com.moa.challenge.mychallenge.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/moa/mychallenge")
public class MyChallengeController {

    // 나의 챌린지 참여 목록 중 하나 들어갔을때
    @GetMapping("/getdetail")
    public void getDetail() {

    }
}
