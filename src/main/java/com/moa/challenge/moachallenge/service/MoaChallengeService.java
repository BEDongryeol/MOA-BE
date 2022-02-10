package com.moa.challenge.moachallenge.service;

import com.moa.challenge.moachallenge.repository.MoaChallengeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MoaChallengeService {

    private final MoaChallengeRepository moaChallengeRepository;

    public List getMoaChallengeList() {
        return moaChallengeRepository.findAll();
    }
}
