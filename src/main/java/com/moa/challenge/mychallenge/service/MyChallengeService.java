package com.moa.challenge.mychallenge.service;

import com.moa.challenge.moachallenge.repository.MoaChallengeRepository;
import com.moa.challenge.moachallenge.vo.MoaChallenge;
import com.moa.challenge.mychallenge.dto.MyChallengeBetCancleDto;
import com.moa.challenge.mychallenge.dto.MyChallengeBetAttendDto;
import com.moa.challenge.mychallenge.dto.MyChallengeBetUpdateDto;
import com.moa.challenge.mychallenge.dto.MyChallengeResponseDto;
import com.moa.challenge.mychallenge.repository.MyChallengeRepository;
import com.moa.challenge.mychallenge.vo.MyChallenge;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MyChallengeService {

    private final MyChallengeRepository myChallengeRepository;
    private final MoaChallengeRepository moaChallengeRepository;

    // 배팅 참여, 배팅했던 키값만큼 유저가 가지고 있는 키를 줄이는 로직 필요
    @Transactional
    public String attendBet(Long id, MyChallengeBetAttendDto requestDto) {

        // dto를 entity화 해서 repository의 save메소드를 통해 db에 저장
        // 모아챌린지의 만료시간과 배팅시간과 비교해서 배팅해도되는시간이라면 save
        LocalDateTime betTime = requestDto.getMyChallengeBetTimer(); // 배팅 한 시간
        List<LocalDateTime> closeTime = moaChallengeRepository.findMoaChallengeByEDate();
        LocalDateTime moaCloseTime = closeTime.get(0); // 만료시간
        // 기존 만료시간이 배팅한 시간 이후 날짜라면 즉 배팅을 해도 되는 시간이라면
        if(moaCloseTime.isAfter(betTime)){
            myChallengeRepository.save(requestDto.toEntity()).getId();
            return "redirect:/compete";
        } else {
            return "false";
        }
    }

    // 배팅 수정, 배팅수정하면 수정했던 배팅키개수를 반영하는 것 구현 필요
    @Transactional
    public String updateBet(Long id, MyChallengeBetUpdateDto requestDto){
        MyChallenge myChallenge = myChallengeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());

        // 모아챌린지의 만료시간과 배팅수정 시간과 비교해서 배팅해도되는시간이라면 save
        LocalDateTime betTime = requestDto.getMyChallengeBetTimer(); // 배팅 한 시간
        List<LocalDateTime> closeTime = moaChallengeRepository.findMoaChallengeByEDate();
        LocalDateTime moaCloseTime = closeTime.get(0); // 만료시간
        // 기존 만료시간이 배팅한 시간 이후 날짜라면 즉 배팅을 해도 되는 시간이라면
        if(moaCloseTime.isAfter(betTime)) {
            myChallenge.update(requestDto.getMyChallengeKey(), requestDto.isMySelected(), requestDto.getMyChallengeBetTimer());
            return "redirect:/compete";
        } else {
            return "false";
        }
    }

    // 배팅 취소
    @Transactional
    public String cancleBet(Long id, MyChallengeBetCancleDto requestDto) {
        Optional<MyChallenge> myChallenge = this.myChallengeRepository.findById(id);
        // 모아챌린지의 만료시간과 배팅수정 시간과 비교해서 배팅해도되는시간이라면 save
        LocalDateTime betTime = requestDto.getMyChallengeBetTimer(); // 배팅 한 시간
        List<LocalDateTime> closeTime = moaChallengeRepository.findMoaChallengeByEDate();
        LocalDateTime moaCloseTime = closeTime.get(0); // 만료시간

        // 삭제하면 배팅했던 키값 가져와서 반영하는 것 구현 필요
        if(myChallenge.isPresent()) {
            if(moaCloseTime.isAfter(betTime)) {
                this.myChallengeRepository.delete(myChallenge.get());
                return "redirect:/compete";
            }
            else {
                return "false";
            }
        }
        return "false";
    }

    // 배팅 조회
    public MyChallengeResponseDto getBetDetail(Long id) {
        MyChallenge entity = myChallengeRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 챌린지가 없습니다."));
        return new MyChallengeResponseDto(entity);
    }

    // 내 챌린지 리스트 조회
    public List getMyChallengeList() {
        return myChallengeRepository.findAll();
    }



}
