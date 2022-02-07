package com.moa.saving.goal.repository;

import com.moa.saving.goal.vo.UserGoal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGoalRepository extends JpaRepository<UserGoal, Long> {
}
