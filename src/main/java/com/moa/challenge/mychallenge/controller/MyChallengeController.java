package com.moa.challenge.mychallenge.controller;

import com.moa.challenge.mychallenge.dto.MyChallengeBetCancleDto;
import com.moa.challenge.mychallenge.dto.MyChallengeBetAttendDto;
import com.moa.challenge.mychallenge.dto.MyChallengeBetUpdateDto;
import com.moa.challenge.mychallenge.dto.MyChallengeResponseDto;
import com.moa.challenge.mychallenge.service.MyChallengeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/challenge/mychallenge")
public class MyChallengeController {

    private final MyChallengeService myChallengeService;

    // 배팅 참여
    @PutMapping("/attend/{id}")
    public String attendBet(@RequestParam(name = "id", required = false) Long id, MyChallengeBetAttendDto requestDto) {
        // dto를 entity화 해서 repository의 save를 통해 db에 저장
        // 저장 후 "redirect:/compete" 반환
        try{
            return myChallengeService.attendBet(id, requestDto);
        }catch (Exception e){
            return "false";
        }
    }

    // 배팅 수정
    @PutMapping("/update/{id}")
    public String updateBet(@RequestParam(name = "id", required = false) Long id, MyChallengeBetUpdateDto requestDto) {
        try{
            return myChallengeService.updateBet(id, requestDto);
        }catch (Exception e) {
            return "false";
        }
    }

    // 배팅 취소
    @PutMapping("/delete/{id}")
    public String cancleBet(@RequestParam(name ="id", required = false) Long id, MyChallengeBetCancleDto requestDto) {
        try {
            return myChallengeService.cancleBet(id, requestDto);
        }catch (Exception e) {
            return "false";
        }
    }

    // 배팅 조회
    @GetMapping("/detail/{id}")
    public MyChallengeResponseDto getBetDetail(@PathVariable Long id) {
        return myChallengeService.getBetDetail(id);
    }

    // 내 챌린지 리스트 조회
    @GetMapping("/mychallenges")
    public List getMyChallengeList() {
        return myChallengeService.getMyChallengeList();
    }
}
