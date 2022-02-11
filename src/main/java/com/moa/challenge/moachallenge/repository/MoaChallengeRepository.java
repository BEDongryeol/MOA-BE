package com.moa.challenge.moachallenge.repository;

import com.moa.challenge.moachallenge.vo.MoaChallenge;
import com.moa.challenge.mychallenge.dto.MyChallengeBetAttendDto;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Repository
public interface MoaChallengeRepository extends JpaRepository<MoaChallenge, Long> {

    // 만료 시간 가져오기
    @Query("select moac.eDate from MoaChallenge moac where moac.id = :id")
    List<LocalDateTime> findMoaChallengeByEDate();

    // 기존 투자했던 키 값 가져오기
    @Query("select u.key from User u where u.id = :id")
    List<Integer> findUserByKey();

}
