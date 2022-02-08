package com.moa.challenge.mychallenge.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/challenge/mychallenge")
public class MyChallengeController {

    // 배팅 참여
    @PutMapping("/attend")
    public void attendBet() {

    }

    // 배팅 수정
    @PutMapping("update")
    public void updateBet() {

    }
    // 배팅 취소
    @PutMapping("delete")
    public void cancleBet() {

    }

    // 배팅 조회
    @GetMapping("/detail")
    public void getDetailBet() {

    }

    // 내 챌린지 리스트 조회
    @GetMapping("/mychallenge")
    public void getMyChallengesList() {

    }
}
