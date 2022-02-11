package com.moa.challenge.mychallenge.controller;

import com.moa.challenge.moachallenge.dto.MoaChallengeResponseDto;
import com.moa.challenge.moachallenge.service.MoaChallengeService;
import com.moa.challenge.mychallenge.dto.MyChallengeBetCancleDto;
import com.moa.challenge.mychallenge.dto.MyChallengeBetAttendDto;
import com.moa.challenge.mychallenge.dto.MyChallengeBetUpdateDto;
import com.moa.challenge.mychallenge.dto.MyChallengeResponseDto;
import com.moa.challenge.mychallenge.service.MyChallengeService;
import com.moa.user.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/challenge/mychallenge")
public class MyChallengeController {

    private final MyChallengeService myChallengeService;
    private final MoaChallengeService moaChallengeService;

    // 배팅 참여
    @PutMapping("/attend/{id}")
    public String attendBet(@RequestParam(name = "id", required = false) Long id, MyChallengeBetAttendDto requestDto) {
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

    // 내 챌린지 리스트 조회
    @GetMapping("/mychallenges/")
    public List getMyChallengeList(HttpSession session) {
        User user = (User) session.getAttribute("user");
        return myChallengeService.getMyChallengeList(user.getId());
    }
}
