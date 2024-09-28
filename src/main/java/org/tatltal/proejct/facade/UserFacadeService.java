package org.tatltal.proejct.facade;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import org.tatltal.proejct.dto.domain.UserDomain;
import org.tatltal.proejct.dto.request.ModifyPushRequest;
import org.tatltal.proejct.dto.request.ModifyUserRequest;
import org.tatltal.proejct.entity.UserEntity;
import org.tatltal.proejct.service.UserService;

@Service
@RequiredArgsConstructor
public class UserFacadeService {
    private final UserService userService;

    public boolean checkNickname(String nickname) {

        return userService.checkNickname(nickname);
    }

    public void modifyUser(ModifyUserRequest request) {
        UserDomain user = userService.getUser(request.getUserId());
        user.modifyUser(request);
        userService.save(new UserEntity(user));

    }

    public void modifyPush(ModifyPushRequest request) {
        UserDomain user = userService.getUser(request.getUserId());
        user.setNotification(request.isPush());
        userService.save(new UserEntity(user));
    }
}
