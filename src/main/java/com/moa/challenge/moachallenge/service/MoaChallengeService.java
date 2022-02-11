package com.moa.challenge.moachallenge.service;

import com.moa.challenge.moachallenge.dto.MoaChallengeResponseDto;
import com.moa.challenge.moachallenge.repository.MoaChallengeRepository;
import com.moa.challenge.moachallenge.vo.MoaChallenge;
import com.moa.challenge.mychallenge.dto.MyChallengeResponseDto;
import com.moa.challenge.mychallenge.vo.MyChallenge;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MoaChallengeService {

    private final MoaChallengeRepository moaChallengeRepository;

    // 배팅 조회 (유저아이디와 모아챌린지의 아이디와 조인해서 가져와야됨)
    @Transactional
    public MoaChallengeResponseDto getBetDetail(Long userId, Long moaChallengeId) {
        MoaChallenge entity = moaChallengeRepository.findById(userId).orElseThrow(()->new IllegalArgumentException("해당 챌린지가 없습니다."));
        return new MoaChallengeResponseDto(entity);
    }

    // 참석자 수 만큼 정렬해서 리턴
    @Transactional
    public List getMoaChallengeList() {
        return moaChallengeRepository.findAll(Sort.by(Sort.Direction.DESC,"challengeCount"));
    }
}
