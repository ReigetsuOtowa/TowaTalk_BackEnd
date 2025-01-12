package com.fuyuzhen.towatalk_backend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "add_friend_list")
@Data
public class AddFriendList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long sendUserId;
    Long receiveUserId;
    String message;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="UTC")
    Date sendTime;
    Integer isReceive = 0;   //0：未处理  1：同意  2：拒绝
}
