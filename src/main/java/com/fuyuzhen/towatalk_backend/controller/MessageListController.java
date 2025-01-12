package com.fuyuzhen.towatalk_backend.controller;

import com.fuyuzhen.towatalk_backend.common.ServerResponse;
import com.fuyuzhen.towatalk_backend.service.MessageListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "message")
public class MessageListController {
    @Autowired
    MessageListService messageListService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    ServerResponse get10message(@RequestParam("sid") Long sid
            , @RequestParam("rid") Long rid){
        return messageListService.getMessages(sid, rid);
    }
}
