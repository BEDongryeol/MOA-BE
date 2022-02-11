package com.moa.user.repository;

import com.moa.challenge.moachallenge.vo.MoaChallenge;
import com.moa.challenge.mychallenge.vo.MyChallenge;
import com.moa.user.vo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.chrono.IsoEra;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLoginId(String loginId);

}

