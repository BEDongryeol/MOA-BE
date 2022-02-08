package com.moa.challenge.moachallenge.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/challenge/moachallenge")
public class MoaChallengeController {

    // 전체 챌린지 리스트(진행, 완료 모두다)
    @GetMapping("/moachallenge")
    public void getMoaChallengesList() {

    }

    // 만료가 되어 챌린지 발표 할 때
    @GetMapping("/anouncece")
    public void announce() {

    }

}
