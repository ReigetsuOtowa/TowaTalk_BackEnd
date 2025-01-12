package com.fuyuzhen.towatalk_backend.service.impl;

import com.fuyuzhen.towatalk_backend.common.ResponseEnum;
import com.fuyuzhen.towatalk_backend.common.ServerResponse;
import com.fuyuzhen.towatalk_backend.entity.User;
import com.fuyuzhen.towatalk_backend.repository.UserRepository;
import com.fuyuzhen.towatalk_backend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public ServerResponse userRegister(User user) {
        try {
            if (StringUtils.isEmpty(user.getUserName())
                    || StringUtils.isEmpty(user.getPassword())
                    || StringUtils.isEmpty(user.getSex())){
                return ServerResponse.getInstance()
                        .responseEnum(ResponseEnum.INVALID_PARAM);
            }
            User cUser = userRepository.findByUserName(user.getUserName());
            if(cUser != null){
                return ServerResponse.getInstance()
                        .responseEnum(ResponseEnum.USERNAME_EXISTS);
            }
            userRepository.save(user);
            return ServerResponse.getInstance()
                    .responseEnum(ResponseEnum.REGISTER_SUCCESS);
        }catch (Exception e){
            log.info(e.getMessage());
            return ServerResponse.getInstance()
                    .responseEnum(ResponseEnum.INNER_ERROR);
        }
    }

    @Override
    public ServerResponse userLogin(User user) {
        try {
            if(StringUtils.isEmpty(user.getUserName())
                    || StringUtils.isEmpty(user.getPassword())){
                return ServerResponse.getInstance()
                        .responseEnum(ResponseEnum.INVALID_PARAM);
            }
            User cUser = userRepository.findByUserNameAndPassword(user.getUserName(), user.getPassword());
            if(cUser == null){
                return ServerResponse.getInstance().responseEnum(ResponseEnum.LOGIN_FAILED);
            }
            cUser.setPassword("");
            return ServerResponse.getInstance().responseEnum(ResponseEnum.LOGIN_SUCCESS).data(cUser);
        }catch (Exception e){
            log.info(e.getMessage());
            return ServerResponse.getInstance()
                    .responseEnum(ResponseEnum.INNER_ERROR);
        }
    }
}
