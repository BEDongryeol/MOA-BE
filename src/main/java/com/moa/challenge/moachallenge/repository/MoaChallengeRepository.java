package com.moa.challenge.moachallenge.repository;

import com.moa.challenge.moachallenge.vo.MoaChallenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoaChallengeRepository extends JpaRepository<MoaChallenge, Long> {
}
