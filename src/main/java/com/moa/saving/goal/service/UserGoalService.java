package com.moa.saving.goal.service;

import com.moa.saving.goal.repository.UserGoalRepository;
import com.moa.saving.goal.vo.UserGoal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserGoalService {

    private final UserGoalRepository userGoalRepository;

    // 전체 모으기 리스트
    public List<UserGoal> getAllList() {
        List<UserGoal> userGoalList = userGoalRepository.findAll();
        return userGoalList;
    }
}
