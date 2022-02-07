package com.moa.challenge.mychallenge.repository;

import com.moa.challenge.mychallenge.vo.MyChallenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyChallengeRepository extends JpaRepository<MyChallenge, Long> {
}
