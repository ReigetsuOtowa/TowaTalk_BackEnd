package com.fuyuzhen.towatalk_backend.controller;

import com.fuyuzhen.towatalk_backend.common.ServerResponse;
import com.fuyuzhen.towatalk_backend.entity.AddFriendList;
import com.fuyuzhen.towatalk_backend.service.AddFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("friend")
public class AddFriendListController {
    @Autowired
    AddFriendService addFriendService;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    ServerResponse addFriend(@RequestBody AddFriendList addFriendList){
        return addFriendService.addFriendReq(addFriendList);
    }

    @RequestMapping(value = "add/list/{userId}", method = RequestMethod.GET)
    ServerResponse listFriend(@PathVariable Long userId){
        return addFriendService.listAddFriendReq(userId);
    }

    @RequestMapping(value = "add/list/handle", method = RequestMethod.POST)
    ServerResponse handleFriend(@RequestParam Long addFriendId,
                              @RequestParam Integer isReceive){
        return addFriendService.handleAddFriend(addFriendId, isReceive);
    }
}
