package com.moa.saving.goal.repository;

import com.moa.saving.goal.vo.UserGoal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserGoalRepository extends JpaRepository<UserGoal, Long> {

}
