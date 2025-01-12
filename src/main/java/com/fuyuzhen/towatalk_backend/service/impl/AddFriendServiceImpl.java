package com.fuyuzhen.towatalk_backend.service.impl;

import com.fuyuzhen.towatalk_backend.common.ResponseEnum;
import com.fuyuzhen.towatalk_backend.common.ServerResponse;
import com.fuyuzhen.towatalk_backend.entity.AddFriendList;
import com.fuyuzhen.towatalk_backend.entity.FriendList;
import com.fuyuzhen.towatalk_backend.entity.User;
import com.fuyuzhen.towatalk_backend.repository.AddFriendListRepository;
import com.fuyuzhen.towatalk_backend.repository.FriendListRepository;
import com.fuyuzhen.towatalk_backend.repository.UserRepository;
import com.fuyuzhen.towatalk_backend.service.AddFriendService;
import com.fuyuzhen.towatalk_backend.vo.AddFriendVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class AddFriendServiceImpl implements AddFriendService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AddFriendListRepository addFriendListRepository;

    @Autowired
    FriendListRepository friendListRepository;
    @Override
    public ServerResponse addFriendReq(AddFriendList addFriendList) {
        long sendUserId = addFriendList.getSendUserId();
        long receiveUserId = addFriendList.getReceiveUserId();

        if (friendListRepository.isFriend(sendUserId, receiveUserId) > 0){
            return ServerResponse.getInstance().code(200).message("请勿重复添加");
        }

        if(null != addFriendListRepository.findBySendUserIdAndReceiveUserIdAndIsReceive(sendUserId,
                receiveUserId, 0)){
            return ServerResponse.getInstance().code(200).message("请等待对方同意");
        }
        addFriendList.setSendTime(new Date());
        try {
            addFriendListRepository.save(addFriendList);
            return ServerResponse.getInstance().code(200).message("发送成功");
        }catch (Exception e){
            e.printStackTrace();
            log.info(e.getMessage());
            return ServerResponse.getInstance()
                    .responseEnum(ResponseEnum.INNER_ERROR);
        }
    }

    @Override
    public ServerResponse handleAddFriend(Long addFriendId, Integer isReceive) {
        try {
            AddFriendList addFriendList = addFriendListRepository.findById(addFriendId).get();
            if (null == addFriendList){
                return ServerResponse.getInstance()
                        .code(200)
                        .message("好友请求不存在");
            }

            addFriendList.setIsReceive(isReceive);
            Long sendUserId = addFriendList.getSendUserId();
            Long receiveUserId = addFriendList.getReceiveUserId();

            User sendUser = userRepository.getOne(sendUserId);
            User receiveUser = userRepository.getOne(receiveUserId);

            List<FriendList> friendLists = new ArrayList<FriendList>();
            FriendList friendListSend = new FriendList();
            friendListSend.setUserId(sendUserId);
            friendListSend.setFriendUserId(receiveUserId);
            friendListSend.setFriendNickName(receiveUser.getUserName());
            friendLists.add(friendListSend);

            FriendList friendListReceive = new FriendList();
            friendListReceive.setUserId(receiveUserId);
            friendListReceive.setFriendUserId(sendUserId);
            friendListReceive.setFriendNickName(sendUser.getUserName());
            friendLists.add(friendListReceive);
            friendListRepository.saveAll(friendLists);

            return ServerResponse.getInstance()
                    .responseEnum(ResponseEnum.ADD_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            log.info(e.getMessage());
            return ServerResponse.getInstance()
                    .responseEnum(ResponseEnum.INNER_ERROR);
        }
    }


    @Override
    public ServerResponse listAddFriendReq(Long userId) {
        try{
            List<Object[]> addFriendLists =
                    addFriendListRepository.selectByReceiveUserIdAndIsReceiveOrderBySendTimeDesc(userId);
            List<AddFriendVo> addFriendVos = new ArrayList<>();
            addFriendLists.forEach(row -> {
                AddFriendVo addFriendVo = new AddFriendVo();
                String userName = (String)row[0];
                Integer sex = (Integer)row[1];
                String avatar = (String)row[2];
                String message = (String)row[3];
                Date send_time = (Date)row[5];
                BigInteger id = (BigInteger)row[6];
                addFriendVo.setUserName(userName);
                addFriendVo.setSex(sex);
                addFriendVo.setAvatar(avatar);
                addFriendVo.setMessage(message);
                addFriendVo.setSendTime(send_time);
                addFriendVo.setId(id.intValue());
                addFriendVos.add(addFriendVo);
            });
            return ServerResponse.getInstance().responseEnum(ResponseEnum.GET_SUCCESS).data(addFriendVos);
        }catch (Exception e){
            e.printStackTrace();
            log.info(e.getMessage());
            return ServerResponse.getInstance()
                    .responseEnum(ResponseEnum.INNER_ERROR);
        }
    }
}
