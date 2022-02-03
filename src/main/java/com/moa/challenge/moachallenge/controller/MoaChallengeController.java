package com.moa.challenge.moachallenge.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/moa/moachallenge")
public class MoaChallengeController {

    // 사용자가 챌린지 메인페이지 볼 때
    @GetMapping("/getAlllist")
    public void getAllList() {

    }

    // 만료가 되어 챌린지 발표 할 때
    @GetMapping("/anouncechallenge")
    public void announceChallenge() {

    }

}
