package org.tatltal.proejct.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tatltal.proejct.entity.FortuneEntity;
import org.tatltal.proejct.repository.FortuneRepository;

import java.util.List;
import java.util.Random;


@RequiredArgsConstructor
@Service
public class FortuneService {

    private final FortuneRepository fortuneRepository;
    private final Random random = new Random();

    public String getRandomDescription() {
        List<FortuneEntity> all = fortuneRepository.findAll();
        int i = random.nextInt(all.size());
        return all.get(i).getText();

    }
}
