package com.fuyuzhen.towatalk_backend.vo;

import lombok.Data;

import java.util.Date;

@Data
public class AddFriendVo {
    Integer id;
    Date sendTime;
    String message;
    String avatar;
    Integer sex;
    String userName;
}
