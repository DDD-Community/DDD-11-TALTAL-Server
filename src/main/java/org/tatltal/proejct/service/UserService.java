package org.tatltal.proejct.service;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.tatltal.proejct.dto.domain.UserDomain;
import org.tatltal.proejct.entity.UserEntity;
import org.tatltal.proejct.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDomain save(UserEntity userEntity) {
        return new UserDomain(userRepository.save(userEntity));
    }

    public UserDomain getUser(ObjectId userId) {

        return new UserDomain(userRepository.findById(userId).orElseThrow());
    }

    public boolean checkNickname(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    public List<UserEntity> getAllNotificationAgreedUser(){

        return userRepository.findByNotificationTrue();

    }
}
