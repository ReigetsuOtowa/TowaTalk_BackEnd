package com.fuyuzhen.towatalk_backend.controller;

import com.fuyuzhen.towatalk_backend.common.ServerResponse;
import com.fuyuzhen.towatalk_backend.service.FriendListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "friend")
public class FriendListController {
    @Autowired
    FriendListService friendListService;

    @RequestMapping(value = "/list/{userId}", method = RequestMethod.GET)
    ServerResponse listAllFriend(@PathVariable Long userId){
        return friendListService.listAllFriendInfo(userId);
    }

    @RequestMapping(value = "/list/un/{userId}", method = RequestMethod.GET)
    ServerResponse listAllUnFriend(@PathVariable Long userId){
        return friendListService.listAllUnFriendInfo(userId);
    }

    @RequestMapping(value = "/list/delete", method = RequestMethod.POST)
    ServerResponse deleteFriend(@RequestParam Long sendUserId,
                                @RequestParam Long receiveUserId){
        return friendListService.deleteFriend(sendUserId, receiveUserId);
    }

}
