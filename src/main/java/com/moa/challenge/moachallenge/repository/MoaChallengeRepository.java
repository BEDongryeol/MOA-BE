package com.moa.challenge.moachallenge.repository;

import com.moa.challenge.moachallenge.vo.MoaChallenge;
import com.moa.challenge.mychallenge.dto.MyChallengeBetAttendDto;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Repository
public interface MoaChallengeRepository extends JpaRepository<MoaChallenge, Long> {

    // 만료 시간 가져오기
    @Query("select moac.eDate from MoaChallenge moac where moac.id =:id")
    List<LocalDateTime> findMoaChallengeByEDate(Long id);

    // 기존 투자했던 키 값 가져오기
    @Query("select u.key from User u where u.id = :id")
    List<Integer> findUserByKey();

    // 배팅 취소시 유저키 더해주기
    @Modifying
    @Query("update User u set u.key = u.key + ?2 where u.id = ?1")
    int updateUserKey(Long id, Integer key);

    // 참석자 수 증가(배팅 참여할 때)
    @Modifying
    @Query("update MoaChallenge moac set moac.challengeCount = moac.challengeCount + 1 where moac.id = :id")
    int updateMoaChallengeCountPlus(Long id);

    // 참석자 수 감소(배팅 취소 할 때)
    @Modifying
    @Query("update MoaChallenge moac set moac.challengeCount = moac.challengeCount - 1 where moac.id = :id")
    int updateMoaChallengeCountMinus(Long id);

    // 모아 챌린지에 참가한 유저 조회(조인 필요)


}
