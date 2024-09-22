package com.plannerapp.service.impl;

import com.plannerapp.model.entity.PriorityEntity;
import com.plannerapp.model.enums.PriorityName;
import com.plannerapp.repo.PriorityRepository;
import com.plannerapp.service.PriorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PriorityServiceImpl implements PriorityService {
    private final PriorityRepository priorityRepository;

    @Autowired
    public PriorityServiceImpl(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }


    @Override
    @PostConstruct
    public void initPriorities() {
        if (this.priorityRepository.count() == 0) {

            List<PriorityEntity> priorities = Arrays.stream(PriorityName.values())
                    .map(PriorityEntity::new)
                    .collect(Collectors.toList());

            for (PriorityEntity p : priorities) {
                if (p.getPriorityName() == PriorityName.URGENT) {
                    p.setDescription("An urgent problem that blocks the system use until the issue is resolved.");
                } else if (p.getPriorityName() == PriorityName.IMPORTANT) {
                    p.setDescription("A core functionality that your product is explicitly supposed to perform is compromised.");
                } else if (p.getPriorityName() == PriorityName.LOW) {
                    p.setDescription("Should be fixed if time permits but can be postponed.");
                }
            }

            this.priorityRepository.saveAll(priorities);
        }
    }
}
