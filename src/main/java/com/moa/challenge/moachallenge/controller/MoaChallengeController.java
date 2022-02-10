package com.moa.challenge.moachallenge.controller;

import com.moa.challenge.moachallenge.service.MoaChallengeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/challenge/moachallenge")
public class MoaChallengeController {

    private final MoaChallengeService moaChallengeService;

    // 전체 챌린지 리스트()
    @GetMapping("/moachallenges")
    public List getMoaChallengesList() {
            return moaChallengeService.getMoaChallengeList();
    }
    
}
