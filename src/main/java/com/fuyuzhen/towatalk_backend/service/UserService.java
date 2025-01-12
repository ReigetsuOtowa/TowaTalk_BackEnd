package com.fuyuzhen.towatalk_backend.service;

import com.fuyuzhen.towatalk_backend.common.ServerResponse;
import com.fuyuzhen.towatalk_backend.entity.User;

public interface UserService {

    ServerResponse userRegister(User user);

    ServerResponse userLogin(User user);
}
