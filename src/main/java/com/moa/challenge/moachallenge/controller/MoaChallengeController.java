package com.moa.challenge.moachallenge.controller;

import com.moa.challenge.moachallenge.dto.MoaChallengeResponseDto;
import com.moa.challenge.moachallenge.service.MoaChallengeService;
import com.moa.user.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/challenge/moachallenge")
public class MoaChallengeController {

    private final MoaChallengeService moaChallengeService;

    // 배팅 조회(수정 필요 -> 전체챌린지 or 내챌린지에서 조회할때 동일하게)
    /*
        아이디 세션과 챌린지
    */
    @GetMapping("/detail/{moaChallengeId}")
    public MoaChallengeResponseDto getBetDetail(@PathVariable(value = "moaChallengeId")Long moaChallengeId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        return moaChallengeService.getBetDetail(user.getId(), moaChallengeId);
    }

    // 전체 챌린지 리스트()
    @GetMapping("/moachallenges")
    public List getMoaChallengesList() {
            return moaChallengeService.getMoaChallengeList();
    }

}
