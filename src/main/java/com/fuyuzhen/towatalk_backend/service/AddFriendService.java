package com.fuyuzhen.towatalk_backend.service;

import com.fuyuzhen.towatalk_backend.common.ServerResponse;
import com.fuyuzhen.towatalk_backend.entity.AddFriendList;

public interface AddFriendService {

    ServerResponse addFriendReq(AddFriendList addFriendList);

    ServerResponse handleAddFriend(Long addFriendId, Integer isReceive);

    ServerResponse listAddFriendReq(Long userId);

}
