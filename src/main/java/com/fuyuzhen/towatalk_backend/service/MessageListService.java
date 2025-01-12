package com.fuyuzhen.towatalk_backend.service;

import com.fuyuzhen.towatalk_backend.common.ServerResponse;
import com.fuyuzhen.towatalk_backend.entity.MessageList;

public interface MessageListService {

    ServerResponse getMessages(Long sendUserId, Long receiveUserId);

    void saveMessage(MessageList messageList);

}
