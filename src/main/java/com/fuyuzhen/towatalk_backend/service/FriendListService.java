package com.fuyuzhen.towatalk_backend.service;

import com.fuyuzhen.towatalk_backend.common.ServerResponse;

public interface FriendListService {

    ServerResponse listAllFriendInfo(Long userId);

    ServerResponse listAllUnFriendInfo(Long userId);

    ServerResponse deleteFriend(Long sendUserId, Long receiveUserId);

}
