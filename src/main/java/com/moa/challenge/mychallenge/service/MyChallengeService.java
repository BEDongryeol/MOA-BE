package com.moa.challenge.mychallenge.service;

import com.moa.challenge.moachallenge.dto.MoaChallengeResponseDto;
import com.moa.challenge.moachallenge.repository.MoaChallengeRepository;
import com.moa.challenge.moachallenge.vo.MoaChallenge;
import com.moa.challenge.mychallenge.dto.MyChallengeBetCancleDto;
import com.moa.challenge.mychallenge.dto.MyChallengeBetAttendDto;
import com.moa.challenge.mychallenge.dto.MyChallengeBetUpdateDto;
import com.moa.challenge.mychallenge.dto.MyChallengeResponseDto;
import com.moa.challenge.mychallenge.repository.MyChallengeRepository;
import com.moa.challenge.mychallenge.vo.MyChallenge;
import com.moa.user.repository.UserRepository;
import com.moa.user.vo.User;
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
    private final UserRepository userRepository;

    // 배팅 참여, 배팅했던 키값만큼 유저가 가지고 있는 키를 줄이는 로직 필요
    // MoaChallenge에 있는 firstbetKey와 secondbetKey에 상황에 맞춰서 배팅한 키값 넣기(총 배팅수 계산을 위해)
    @Transactional
    public String attendBet(Long id, MyChallengeBetAttendDto requestDto) {

        // dto를 entity화 해서 repository의 save메소드를 통해 db에 저장
        // 모아챌린지의 만료시간과 배팅시간과 비교해서 배팅해도되는시간이라면 save
        LocalDateTime betTime = requestDto.getMyChallengeBetTimer(); // 배팅 한 시간
        List<LocalDateTime> closeTime = moaChallengeRepository.findMoaChallengeByEDate(id);
        LocalDateTime moaCloseTime = closeTime.get(0); // 만료시간
        // 기존 만료시간이 배팅한 시간 이후 날짜라면 즉 배팅을 해도 되는 시간인지 체크
        if(moaCloseTime.isAfter(betTime)){
//            if(requestDto.isMySelected() == true) // 총 배팅수 계산을 위해 moaChallenge의 각 키에 넣는 로직
//                moaChallengeRepository.save(requestDto.toMoaChallengeFirstKeyEntity()).getId();
//            else
//                moaChallengeRepository.save(requestDto.toMoaChallengeSecondKeyEntity()).getId();
            myChallengeRepository.save(requestDto.toMyChallengeEntity()).getId();
            // 참석자수 증가
            moaChallengeRepository.updateMoaChallengeCountPlus(id);
            return "redirect:/compete";
        } else {
            return "false";
        }
    }

    // 배팅 수정,
    @Transactional
    public String updateBet(Long id, MyChallengeBetUpdateDto requestDto){
        MyChallenge myChallenge = myChallengeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());

        // 모아챌린지의 만료시간과 배팅수정 시간과 비교해서 배팅해도되는시간이라면 save
        LocalDateTime betTime = requestDto.getMyChallengeBetTimer(); // 배팅 한 시간
        List<LocalDateTime> closeTime = moaChallengeRepository.findMoaChallengeByEDate(id);
        LocalDateTime moaCloseTime = closeTime.get(0); // 만료시간
        // 기존 만료시간이 배팅한 시간 이후 날짜라면 즉 배팅을 해도 되는 시간이라면
        if(moaCloseTime.isAfter(betTime)) {
            myChallenge.update(requestDto.getMyChallengeKey(), requestDto.isMySelected(), requestDto.getMyChallengeBetTimer());
            // 배팅수정하면 수정했던 배팅키개수를 반영하는 것 구현 필요
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
        List<LocalDateTime> closeTime = moaChallengeRepository.findMoaChallengeByEDate(id);
        LocalDateTime moaCloseTime = closeTime.get(0); // 만료시간

        // 삭제하면 배팅했던 키값 가져와서 반영하는 것 구현 필요
        if(myChallenge.isPresent()) {
            if(moaCloseTime.isAfter(betTime)) {
                this.myChallengeRepository.delete(myChallenge.get());
                // 참석자수 줄이는 로직
                moaChallengeRepository.updateMoaChallengeCountMinus(id);
                // 배팅 취소한 만큼 유저의 키를 가져와 더해줘야되는 로직 필요
                int userKey = requestDto.getMyChallengeKey(); // 배팅했던 키 가져오기
                this.moaChallengeRepository.updateUserKey(id, userKey);
                return "redirect:/compete";
            }
            else {
                return "false";
            }
        }
        return "false";
    }

    // 내 챌린지 리스트 조회
    public List getMyChallengeList(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.get().getMyChallenge();
    }
}
